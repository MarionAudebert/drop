package com.badlogic.drop;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;

public class Bucket {
	
	private Rectangle bucket;
	private Texture bucketImage;
	private SpriteBatch batch;
	private OrthographicCamera camera;
	
	
	public void create (SpriteBatch batch, OrthographicCamera camera) {
		bucketImage = new Texture(Gdx.files.internal("bucket.png"));
		this.batch = batch;
		this.camera = camera;
		
		bucket = new Rectangle();
		bucket.x = 800 / 2 - 64 / 2 ;
		bucket.y = 20;
		bucket.width = 64;
		bucket.height = 64;
	}
	
	public void draw() {
		batch.draw(bucketImage, bucket.x, bucket.y);
	}
	
	public Rectangle getRectangle() {
		return this.bucket;
	}
	
	public void render() {
		
		if (Gdx.input.isTouched()){
			Vector3 touchPos = new Vector3();
			touchPos.set(Gdx.input.getX(), Gdx.input.getY(), 0);
			camera.unproject(touchPos);
			bucket.x = touchPos.x - 64 / 2;
		}
		if(Gdx.input.isKeyPressed(Keys.LEFT)) 
			bucket.x -= 200 * Gdx.graphics.getDeltaTime();
		if(Gdx.input.isKeyPressed(Keys.RIGHT))
			bucket.x += 200 * Gdx.graphics.getDeltaTime();
		if(bucket.x < 0) 
			bucket.x = 0;
		if(bucket.x > 800 - 64) 
			bucket.x = 800 - 64;
	}
	
	public void dispose() {
		bucketImage.dispose();
	}

}
