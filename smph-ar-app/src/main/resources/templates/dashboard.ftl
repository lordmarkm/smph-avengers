<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<title>SM AR Dashboard!</title>
<link href="/css/main.css" rel="stylesheet">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
</head>
<body>
<div class="container">
  <div class="row mt-2 mb-5">
    <div class="card col-12 text-right">
      Hi, admin!
      <a href="/logout">Logout</a>
    </div>

    <#if msgs??>
    <div class="card col-12 mt-2 text-white bg-success">
      <#list msgs as msg>
      <div>${msg}</div>
      </#list>
    </div>
    </#if>

    <div class="card col-12 mt-2">
    <h4 class="card-title mt-1">Hello! These are the coupon code usage statistics</h4>
    <div class="card-text">
      <table class="table table-striped">
        <tr>
          <th>Valid</th>
          <th>${dashboard.couponCodeValid}
        </tr>
        <tr>
          <th>Invalid</th>
          <th>${dashboard.couponCodeInvalid}
        </tr>
        <tr>
          <th>Redeemed</th>
          <th>${dashboard.couponCodeRedeemed}
        </tr>
        <tr>
          <th>Total</th>
          <th>${dashboard.couponCodeTotal}
        </tr>
      </table>
      <a href="/db" class="btn btn-primary mb-5">View all</a>
    </div>
    </div>
    <!-- end first panel -->

    <div class="card col-12 mt-2">
    <h4 class="card-title mt-2">Upload more codes</h4>
    <p class="card-text">File content should look like this:
    <pre>
    <code>
    Samplecode12345
    Samplecode4546
    Samplecode2222
    </code>
    </pre>
    <div class="card-text">
      <form method="post" enctype="multipart/form-data" action="/coupon-code/upload">
        <div class="form-group">
          <input type="file" name="file">
        </div>
        <div class="form-group">
          <button type="submit" class="btn btn-primary mb-1">Upload</button>
        </div>
      </form>
    </div>
    </div>
    <!-- end upload panel -->

  </div>
</div>
</body>
</html>