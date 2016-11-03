<?php
$sexo = $_GET["sexo"]; 
$nombre = $_GET["nombre"];
$edad = $_GET["edad"];
$color="white";
$url;

if($sexo=="hombre")
 $color="powderblue";
else
  $color="pink";     

if($edad <= 10)
  $url = "https://i.ytimg.com/vi/5-OLGVMlimw/maxresdefault.jpg";
elseif($edad>10 && $edad<21)
  $url = "http://www.imaf.es/wp-content/uploads/2016/07/estudio-revela-que-estadounidenses-gastan-cada-vez-mas-en-videojuegos-02-e1445888221548.jpg";
else 
  $url = "https://www.enfoque.global/wp-content/uploads/2015/09/leer-libros-8-9.png";
  

?>

<html>
  <body style="background-color:<?php echo $color;?>;">
    <H1>
      Hola, <?php echo $nombre; ?>
    </H1>
    <img src="<?php echo $url; ?>" style="width:304px;height:228px;"/> 
  </body>
</html>