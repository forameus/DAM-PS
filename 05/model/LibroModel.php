<?php


class LibroModel implements JsonSerializable
{
    private $titulo;
    private $id;

    public function __construct($id,$tit)
    {
        $this->id=$id;
        $this->titulo=$tit;
    }


    function jsonSerialize()
    {
        return array(
            'id' => $this->id,
            'titulo' => $this->titulo
        );
    }


    public function __sleep(){
        return array('id','titulo');
    }


    public function getTitulo()
    {
        return $this->titulo;
    }

    /**
     * @param mixed $titulo
     */
    public function setTitulo($titulo)
    {
        $this->titulo = $titulo;
    }

    /**
     * @return mixed
     */
    public function getCodigo()
    {
        return $this->id;
    }

    /**
     * @param mixed $codigo
     */
    public function setCodigo($id)
    {
        $this->id = $id;
    }



}