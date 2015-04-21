package com.badlogic.drop;

import java.util.Iterator;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.TimeUtils;

public class Raindrops {
	
	private Array<Rectangle> raindrops;
	private long lastDropTime;
	private Texture dropImage;
	private SpriteBatch batch;
	private OrthographicCamera camera;
	
	private void spawnRaindrop() {
		Rectangle raindrop = new Rectangle();
		raindrop.x = MathUtils.random(0, 800-64);
		raindrop.y = 480;
		raindrop.width = 64;
		raindrop.height = 64;
		raindrops.add(raindrop);
		lastDropTime = TimeUtils.nanoTime();
		
	}
	
	public void create(SpriteBatch batch, OrthographicCamera camera){
		
		this.batch = batch;
		this.camera = camera;
		dropImage = new Texture(Gdx.files.internal("droplet.png"));
		raindrops = new Array<Rectangle>();
		spawnRaindrop();
	}
	
	public void draw() {
		for (Rectangle raindrop : raindrops) {
			batch.draw(dropImage, raindrop.x, raindrop.y);
		}
	}
	
	public void render(Bucket bucket) {
		if (TimeUtils.nanoTime() - lastDropTime > 1000000000)
			spawnRaindrop();
		
		Iterator<Rectangle> iter = raindrops.iterator();
		while(iter.hasNext()) {
			Rectangle raindrop = iter.next();
			raindrop.y -= 200 * Gdx.graphics.getDeltaTime();
			if (raindrop.y + 64 < 0)
				iter.remove();
			if (raindrop.overlaps(bucket.getRectangle())) 
				iter.remove();
		}
		
	}
	
	public void dispose() {
		dropImage.dispose();
	}

}
