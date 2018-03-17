<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="ie=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">

  <link rel="icon" type="image/jpg" href="https://png.icons8.com/color/1600/google-maps.png">
  <title> Mark on GG Maps</title>
</head>
<body>
      <div class="container">
              <form method="post" action="map.php">
                    <label for="latitude"><b>Vĩ độ điểm đánh dấu:   </b></label>
                    <input type="text" name="latitude" required>
                    </br>
                    <label for="longitude"><b>Kinh độ điểm đánh dấu: </b></label>
                    <input type="text" name="longitude" required>
                    
                    <p style="color:red; font-size: 14px;"><i>(Nhập thêm toạ độ điểm đến để tính khoảng cách giữa hai điểm)</i></p>
                    
                    <label for="slat"><b>Vĩ độ điểm đến</b></label>
                    <input type="text" value="" name="slat">
                    </br>
                    <label for="slng"><b>Kinh độ điểm đến</b></label>
                    <input type="text" value="" name="slng">
                    </br>
                    <button type="submit">Đánh dấu toạ độ / Tính khoảng cách 2 điểm</button>
                    </form>
      </div>
</body>
</html>