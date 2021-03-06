package inf1.oop.turnbased.graphics;

import inf1.oop.turnbased.ServiceProvider;
import inf1.oop.turnbased.map.Map;
import inf1.oop.turnbased.map.Tile;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class MapRenderer {
	AssetManager assets;
	SpriteBatch batch;
	RenderingParameters renderParams;
	
	Map currentMap;
	
	public MapRenderer(ServiceProvider services) {
		assets = services.get(AssetManager.class);
		batch = services.get(SpriteBatch.class);
		renderParams = services.get(RenderingParameters.class);
	}
	
	public void setMap(Map newMap) { currentMap = newMap; }
	
	// xShift = How far along the x axis to start drawing
	// yShift = Same for y axis
	public void draw(float xShift, float yShift) {
		if(currentMap == null) throw new NullPointerException();
		
		int w = currentMap.getTileWidth();
		int h = currentMap.getTileHeight();
		
		float xOffset = renderParams.getXOffset() + xShift;
		float yOffset = renderParams.getYOffset() + yShift;
		
		
		//perhaps *some* method to create an image after reading once would mean faster rendering
		for(int x = 0; x < currentMap.getWidth(); x++) {
			for(int y = 0; y < currentMap.getHeight(); y++) {
				Tile t = currentMap.getTile(x, y);
				
				if(t != null && t.hasTexture()) {
					batch.draw((Texture)assets.get(t.getTextureName()), xOffset + (x*w), yOffset + (y*h));
				}
			}
		}
	}
	
	public void draw() { draw(0,0); }
}
