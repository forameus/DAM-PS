<?php

require_once "Controller.php";


class ProductoController extends Controller
{
    public function manageGetVerb(Request $request)
    {

        $productos = null;
        $id = null;
        $response = null;
        $code = null;

        if (isset($request->getUrlElements()[2])) {
            $id = $request->getUrlElements()[2];
        }


        $productos = ProductoHandlerModel::getProducto($id);

        if ($productos != null) {
            $code = '200';

        } else {
            if (ProductoHandlerModel::isValid($id)) {
                $code = '404';
            } else {
                $code = '400';
            }

        }

        $response = new Response($code, null, $productos, $request->getAccept());
        $response->generate();

    }

    public function managePostVerb(Request $request)
    {
        $cod = $request->getBodyParameters()->cod;
        $nombre = $request->getBodyParameters()->nombre;
        $descripcion = $request->getBodyParameters()->descripcion;
        $precio = $request->getBodyParameters()->precio;


        if(!ProductoHandlerModel::isValid($cod) || $precio<0 || empty($nombre) || empty($descripcion)) {
            $code = '400';
            echo 'Producto no valido.';
        }

        else {
            $producto = new ProductoModel($cod, $nombre, $descripcion, $precio);
            $code = ProductoHandlerModel::putProducto($producto);
        }

        $response = new Response($code);
        $response->generate();
    }

}