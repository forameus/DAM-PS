<html>

<body>
  <form action="salida.php" method="post">
    <?php
      $numero = $_POST["numero"];

        for($i = 0; $i < $numero; $i++){
          echo "Introduzca un nombre:<input type='text' name='nombre".$i."'/><br/>";
        }   
    ?>


      <input type="hidden" name="numero" value="<?php echo $numero;?>" />
      <input type="submit" />
  </form>
</body>

</html>