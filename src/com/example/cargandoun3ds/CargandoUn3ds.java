package com.example.cargandoun3ds;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import min3d.core.Object3dContainer;
import min3d.core.RendererActivity;
import min3d.parser.IParser;
import min3d.parser.Parser;
import min3d.vos.Light;

public class CargandoUn3ds extends RendererActivity {//estendemos de renderActivity

	private final float CAM_RADIUS_X = 20;
	private final float CAM_RADIUS_Y = 15;
	private final float CAM_RADIUS_Z = 30;
	private final float ROTATION_SPEED = 1; //valores para rotacion
	
	private Object3dContainer objeto3d;//crear un objeto para cargar el 3ds 
	
	private float degrees;

	@Override
	public void initScene() {
		
		scene.lights().add(new Light());

		IParser parser = Parser.createParser(Parser.Type.MAX_3DS,//tipo de objeto puede ser obj ,3ds o md2.
				getResources(), "com.example.cargandoun3ds:raw/esfera", false);//verificar bien esta ruta aveces no carga
		parser.parse();

		objeto3d = parser.getParsedObject();
		objeto3d.scale().x = objeto3d.scale().y = objeto3d.scale().z  = .5f;//escalarlo
		objeto3d.position().y = -10;//pocisionarlo
		scene.addChild(objeto3d);
		
		scene.camera().target = objeto3d.position();//se añade a la actividad
	}

	@Override
	public void updateScene() {//todo este codigo es para la rotacion
		float radians = degrees * ((float)Math.PI / 180);
		
		scene.camera().position.x = (float)Math.cos(radians) * CAM_RADIUS_X; //rotacion en el eje x
		scene.camera().position.y = (float)Math.sin(radians) * CAM_RADIUS_Y;//rotacion en el eje y
		scene.camera().position.z = (float)Math.sin(radians) * CAM_RADIUS_Z;//rotacion en el eje z

		degrees += ROTATION_SPEED;
	}
	
}
//bueno espero les haya gustado el tutorial