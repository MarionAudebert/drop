package com.badlogic.drop;

import java.util.Iterator;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.TimeUtils;

public class Drop extends ApplicationAdapter {
	
	private OrthographicCamera camera;
	private SpriteBatch batch;
	
	private Bucket bucket;
	private Raindrops raindrops;
	

	
	
	@Override
	public void create () {
		
		
		camera = new OrthographicCamera();
		camera.setToOrtho(false, 800, 480);
		
		batch = new SpriteBatch();
		
		bucket = new Bucket();
		bucket.create(batch, camera);
		raindrops = new Raindrops();
		raindrops.create(batch, camera);
		
		
		
	}

	@Override
	public void render () {
		
		Gdx.gl.glClearColor(0, 0, 0.2F, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		camera.update();
		
		batch.setProjectionMatrix(camera.combined);
		batch.begin();
		  bucket.draw();
		  raindrops.draw();
		batch.end();
		
		bucket.render();
		raindrops.render(bucket);
		
		
	}
	
	public void dispose() {
		bucket.dispose();
		raindrops.dispose();
		batch.dispose();
	}
}
