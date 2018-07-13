package com.smph.ar.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.RandomStringUtils;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.amazonaws.util.IOUtils;

@Controller
@RequestMapping("/logs")
public class LogfileDownloadController {

    private static final Logger LOG = LoggerFactory.getLogger(LogfileDownloadController.class);
    private static final String DATE_FORMAT = "yyyy-MMM-dd";

    @GetMapping
    public void downloadLogfile(HttpServletResponse response, @RequestParam @DateTimeFormat(pattern = DATE_FORMAT) Optional<DateTime> date) throws IOException {
        LOG.info("LogfileDownloadController::downloadLogfile(..,{})", date);
        String dateStr = date.map(d -> d.toString(DATE_FORMAT)).orElse("");
        String prefix = "smph-ar." + dateStr;
        List<Path> paths = Files.find(Paths.get("log/"), 1,
                (path, attr) -> {
                    LOG.debug("Searching path. path={}", path);
                    return date.isPresent() ? path.toFile().getName().contains(prefix) : path.toFile().getName().equals("smph-ar.log");
                })
                .collect(Collectors.toList());

        LOG.debug("Found logfile paths. Writing to response... paths={}", paths);

        File tmpFile = new File("tmp", "log.zip");
        try {
            String filename = date.map(d -> "logs." + d.toString(DATE_FORMAT) + ".zip").orElse("logs.zip");
            response.setHeader("Content-Disposition", "attachment; filename=" + filename);

            addFilesToZip(paths, tmpFile);
            FileInputStream fis = new FileInputStream(tmpFile);
            IOUtils.copy(fis, response.getOutputStream());
            response.flushBuffer();
        } catch (IOException e) {
            LOG.error("Unable to create files for download.", e);
        } finally {
            tmpFile.delete();
        }
    }

    private void addFilesToZip(List<Path> paths, File tmpFile) throws FileNotFoundException, IOException {
        LOG.debug("Trying to create zip file in tmp location. file={}", tmpFile);
        tmpFile.getParentFile().mkdirs();
        tmpFile.createNewFile();
        FileOutputStream fos = new FileOutputStream(tmpFile);
        ZipOutputStream zos = new ZipOutputStream(fos);
        paths.forEach(path -> {
            try {
                File file = path.toFile();
                if (!file.exists()) {
                    throw new IllegalArgumentException("File was not actually found. path=" + path);
                }
                FileInputStream fis = new FileInputStream(file);
                ZipEntry zipEntry = new ZipEntry(file.getName());
                zos.putNextEntry(zipEntry);

                byte[] bytes = new byte[1024];
                int length;
                while ((length = fis.read(bytes)) >= 0) {
                    zos.write(bytes, 0, length);
                }

                zos.closeEntry();
                fis.close();
            } catch (IOException e) {
                LOG.warn("Could not zip log file! file={}", path, e);
            }
        });
        zos.close();
    }

}
