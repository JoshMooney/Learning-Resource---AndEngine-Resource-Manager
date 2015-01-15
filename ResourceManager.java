package ie.itcarlow.box2ddemo;

import java.io.IOException;

import org.andengine.audio.music.Music;
import org.andengine.audio.sound.Sound;
import org.andengine.audio.sound.SoundFactory;
import org.andengine.engine.Engine;
import org.andengine.engine.camera.Camera;
import org.andengine.entity.sprite.Sprite;
import org.andengine.opengl.texture.TextureOptions;
import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlas;
import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlasTextureRegionFactory;
import org.andengine.opengl.texture.atlas.bitmap.BuildableBitmapTextureAtlas;
import org.andengine.opengl.texture.atlas.bitmap.source.IBitmapTextureAtlasSource;
import org.andengine.opengl.texture.atlas.buildable.builder.BlackPawnTextureAtlasBuilder;
import org.andengine.opengl.texture.region.ITextureRegion;
import org.andengine.opengl.vbo.VertexBufferObjectManager;
import org.andengine.util.debug.Debug;

public class ResourceManager 
{
	private static final ResourceManager INSTANCE = new ResourceManager();
	
	public static ResourceManager getInstance()
	{
		return INSTANCE;
	}
	
	public Engine engine;
	public Box2DSpriteCollisions activity;
	public Camera camera;
	public VertexBufferObjectManager vbom;
	
	public static void prepareManager(Engine engine, Box2DSpriteCollisions activity, android.graphics.Camera camera2, VertexBufferObjectManager vbom)
	{
		getInstance().engine = engine;
		getInstance().activity = activity;
		getInstance().camera = camera2;
		getInstance().vbom = vbom;
	}
	
	private BuildableBitmapTextureAtlas menuTextureAtlas;
	public ITextureRegion play_button_region;
	public ITextureRegion exit_button_region;
	public Music menuBE;
	public Sound menuClickedSE;
	
	private BuildableBitmapTextureAtlas tiledTextureAtlas;
	public ITextureRegion wall_region;
	public ITextureRegion floor_region;
	

	//MoveTo Variable will be location for A* to move to
	//private Vector2 MoveTo;
	
	private BuildableBitmapTextureAtlas gameTextureAtlas;
	//PlayerOne
	private BuildableBitmapTextureAtlas PlayerOneAtlas;
	public ITextureRegion mPlayerOneTextureRegion;
	
	//PlayerTwo
	private BuildableBitmapTextureAtlas PlayerTwoAtlas;
	public ITextureRegion mPlayerTwoTextureRegion;
	
	
	
	public Sprite mPlayerTwo;
		
	
	public TileManager tileManager;
	public int tileSize = 50;
	
	public void LoadMenuResources()
	{
		BitmapTextureAtlasTextureRegionFactory.setAssetBasePath("gfx/Menu/"); 
		menuTextureAtlas = new BuildableBitmapTextureAtlas(activity.getTextureManager(), 1024, 1024, TextureOptions.BILINEAR);
		play_button_region = BitmapTextureAtlasTextureRegionFactory.createFromAsset(menuTextureAtlas, activity, "play.png");
		exit_button_region = BitmapTextureAtlasTextureRegionFactory.createFromAsset(menuTextureAtlas, activity, "exit.png");
	
		try{
			menuTextureAtlas.build(new BlackPawnTextureAtlasBuilder<IBitmapTextureAtlasSource, BitmapTextureAtlas>(0,1,0));
			menuTextureAtlas.load();
		}
		catch (Exception e){
			Debug.e(e);
		}
	}
	public void UnloadMenuResources()
	{
		menuTextureAtlas.unload();
		menuTextureAtlas = null;
	}
	
	public void LoadGameResources()
	{
		BitmapTextureAtlasTextureRegionFactory.setAssetBasePath("gfx/");
		gameTextureAtlas = new BuildableBitmapTextureAtlas(activity.getTextureManager(), 65, 65, TextureOptions.BILINEAR);
		
		//PlayerOne
        PlayerOneAtlas = new BuildableBitmapTextureAtlas(activity.getTextureManager(), 65, 65);  
        mPlayerOneTextureRegion = BitmapTextureAtlasTextureRegionFactory.createFromAsset(PlayerOneAtlas, activity, "playerOne.png");
        
        //PlayerTwo
        PlayerTwoAtlas = new BuildableBitmapTextureAtlas(activity.getTextureManager(), 65, 65);  
        mPlayerTwoTextureRegion = BitmapTextureAtlasTextureRegionFactory.createFromAsset(PlayerTwoAtlas, activity, "playerTwo.png");
	
		try{
			gameTextureAtlas.build(new BlackPawnTextureAtlasBuilder<IBitmapTextureAtlasSource, BitmapTextureAtlas>(0,1,0));
			gameTextureAtlas.load();
		}
		catch (Exception e){
			Debug.e(e);
		}
	}	
    public void UnloadGameResources()
    {
    	gameTextureAtlas.unload();
		gameTextureAtlas = null;
    }
	
    public void LoadTileResources()
    {
    	BitmapTextureAtlasTextureRegionFactory.setAssetBasePath("gfx/Map/"); 
    	tiledTextureAtlas = new BuildableBitmapTextureAtlas(activity.getTextureManager(), 256, 256);
    	
    	wall_region = BitmapTextureAtlasTextureRegionFactory.createFromAsset(tiledTextureAtlas, activity, "Wall.png");
    	floor_region = BitmapTextureAtlasTextureRegionFactory.createFromAsset(tiledTextureAtlas, activity, "Floor.png");
    	
    	try{
    		tiledTextureAtlas.build(new BlackPawnTextureAtlasBuilder<IBitmapTextureAtlasSource, BitmapTextureAtlas>(0,1,0));
    		tiledTextureAtlas.load();
		}
		catch (Exception e){
			Debug.e(e);
		}
    }
    public void UnloadTileResources()
    {
    	tiledTextureAtlas.unload();
    	tiledTextureAtlas = null;
    }
    
    public void LoadFonts()
	{
		
	}	
	public void LoadMenuAudio()
	{
		SoundFactory.setAssetBasePath("mfx/Menu");
		try 
		{
			menuBE = SoundFactory.createSoundFromAsset(this.engine.getSoundManager(), activity, "mySoundFile.ogg");
		} 
		catch (final IOException e) 
		{
			Debug.e(e);
		}
		menuClickedSE;
	}
	
	public void LoadGameAudio()
	{
	
	}
	
	public void LoadTileManager()
	{	
		LoadTileResources();
		tileManager = new TileManager(vbom);
	}
}
