<?php
class utiles_texto{
  /*
  num_veces_palabra($texto, $palabra)
  desc: cuenta las veces que se repite una palabra en un texto
  entradas: el texto y la palabra
  salidas: entero
  postcondiciones: devuelve las veces que se repite la palabra en el texto
  */
  function num_veces_palabra($texto, $palabra){
    $res = 0;
    $palabras = str_word_count($texto, 1);
    foreach($palabras as $temp){
      if($temp==$palabra)
        $res++;
    }
    return $res;
  }
  
  /*
  posiciones_palabra($texto, $palabra)
  desc: devuelve las posiciones en que se repite una palabra en un texto
  entradas: el texto y la palabra
  salidas: array de enteros
  postcondiciones: devuelve un array con las posiciones en las que se repite la palabra en el texto
  */
  function posiciones_palabra($texto, $palabra){
    $i = 1;
    $pos = 0;
    $posiciones;
    $palabras = str_word_count($texto, 1);
    foreach($palabras as $temp){
      if($temp==$palabra){
        $pos++;
        $posiciones[$pos]=$i;
      }
      $i++;
    }
    return $posiciones;
  }
  
   /*
  sustituye_palabras($texto, $palabra1, $palabra2)
  desc: sustituye una palabra por otra en un texto
  entradas: el texto, la palabra antigua y la nueva
  salidas: texto
  postcondiciones: sustituye una palabra por otra en un texto
  */
  function sustituye_palabras($texto, $palabra1, $palabra2){
    $i = 1;
    $pos = 0;
    $nuevo_texto;
    $palabras = str_word_count($texto, 1);
    foreach($palabras as $temp){
      if($temp==$palabra1){
        $nuevo_texto[$i]=$palabra2;
      }
      else{
        $nuevo_texto[$i]=$temp; 
      }
      $i++;
    }
    return implode(" ",$nuevo_texto);
  }
   /*
  sustituye_palabras($texto, $palabra1, $palabra2)
  desc: intercambia una palabra deuna posición por otra en un texto segun las posiciones
  entradas: el texto, 2 posiciones
  salidas: texto
  postcondiciones: intercambia la palabra en la primera posición dada por la palabra en la segunda
  */
  function intercambia_palabras($texto, $pos1, $pos2){
    
    $palabras = str_word_count($texto, 1);
    
    $pos1--;$pos2--;
    
    $aux = $palabras[$pos1];
    $palabras[$pos1] = $palabras[$pos2];    
    $palabras[$pos2] = $aux; 
  
    return implode(" ",$palabras);
  }
 
  /*
  NO SE COMO SE ASE
  invierte_texto($texto)
  desc: pasa un texto al sentido contrario  
  entradas: texto
  salidas: texto
  postcondiciones: el texto devuleto tiene las letras en sentido inverso
  */
  /*function invierte_texto($texto){
    
   
    $letras = str_split($texto);
    $res = $letras;
    $j =strlen($texto);
   
    /*for ($i = 0; $i < strlen($texto); $i++) {
      
      $res = array(
        $i => $letras[$j]      
      );   
      $j--;
    }*/    

}