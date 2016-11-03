<html>

<body>
  <?php
      $numero = $_POST["numero"];
      
       for($i = 0; $i < $numero; $i++){
          echo $_POST["nombre".$i]."<br/>";
        }
    ?>
</body>

</html>