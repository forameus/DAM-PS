<html>

<body>
  <?php
   require_once "utiles_texto.php";
   $ut = new utiles_texto();
   echo "Texto: \"".$_POST["texto"]."\" <br/>";
  
   if($_POST["contar"])
     echo "<br/> - La palabra \"".$_POST["palabra"]."\" aparece ".$ut->num_veces_palabra( $_POST["texto"], $_POST["palabra"])." veces";
   if($_POST["posiciones"])
     if(!is_null($ut->posiciones_palabra( $_POST["texto"], $_POST["palabra_pos"])))
       echo "<br/> - La palabra \"".$_POST["palabra_pos"]."\" aparece en las posiciones: ".implode(", ",$ut->posiciones_palabra( $_POST["texto"], $_POST["palabra_pos"]));
     else
       echo "<br/> - La palabra \"".$_POST["palabra_pos"]."\" no aparece en el texto";
   if($_POST["sustituir"])
     echo "<br/> - ".$ut->sustituye_palabras($_POST["texto"], $_POST["palabra_sus1"], $_POST["palabra_sus2"]);
   if($_POST["intercambiar"])
     echo "<br/> - ".$ut->intercambia_palabras($_POST["texto"], $_POST["pos_int1"], $_POST["pos_int2"]);
   if($_POST["invertir"])
     echo "<br/> - ".$ut->invierte_texto($_POST["texto"]);
    //echo "FUNCA";
  ?>
</body>

</html>