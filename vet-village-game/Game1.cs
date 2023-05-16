using Microsoft.Xna.Framework;
using Microsoft.Xna.Framework.Graphics;
using Microsoft.Xna.Framework.Input;
using System;

namespace vet
{
    /// <summary>
    /// This is the main type for your game.
    /// </summary>
    public class Game1 : Game
    {
        // graphics
        private GraphicsDeviceManager graphics;
        private SpriteBatch spriteBatch;
        private const float SCALE = 1f;

        public static Vector2 frameSize = new Vector2(0, 0);

        public static Random rand = new Random();



        public Game1()
        {
            graphics = new GraphicsDeviceManager(this);
            Content.RootDirectory = "Content";
            graphics.PreferredBackBufferWidth = GraphicsAdapter.DefaultAdapter.CurrentDisplayMode.Width - (int)(GraphicsAdapter.DefaultAdapter.CurrentDisplayMode.Width*0.2);
            graphics.PreferredBackBufferHeight = GraphicsAdapter.DefaultAdapter.CurrentDisplayMode.Height - (int)(GraphicsAdapter.DefaultAdapter.CurrentDisplayMode.Height * 0.2);
            frameSize.X = graphics.PreferredBackBufferWidth;
            frameSize.Y = graphics.PreferredBackBufferHeight;
            IsFixedTimeStep = true;
            IsMouseVisible = true;
        }

        /// <summary>
        /// Allows the game to perform any initialization it needs to before starting to run.
        /// This is where it can query for any required services and load any non-graphic
        /// related content.  Calling base.Initialize will enumerate through any components
        /// and initialize them as well.
        /// </summary>
        protected override void Initialize()
        {
            // TODO: Add your initialization logic here

            base.Initialize();
        }

        /// <summary>
        /// LoadContent will be called once per game and is the place to load
        /// all of your content.
        /// </summary>
        protected override void LoadContent()
        {
            // Create a new SpriteBatch, which can be used to draw textures.
            spriteBatch = new SpriteBatch(GraphicsDevice);

            // TODO: use this.Content to load your game content here
            SpriteSheet.Init(Content);
            Map.Init();
            MovingObject.InitNonPlayers();
            Player.Init();

        }

        /// <summary>
        /// UnloadContent will be called once per game and is the place to unload
        /// game-specific content.
        /// </summary>
        protected override void UnloadContent()
        {
            // TODO: Unload any non ContentManager content here
        }

        /// <summary>
        /// Allows the game to run logic such as updating the world,
        /// checking for collisions, gathering input, and playing audio.
        /// </summary>
        /// <param name="gameTime">Provides a snapshot of timing values.</param>
        protected override void Update(GameTime gameTime)
        {
            if (GamePad.GetState(PlayerIndex.One).Buttons.Back == ButtonState.Pressed || Keyboard.GetState().IsKeyDown(Keys.Escape))
                Exit();

            // TODO: Add your update logic here

            KeyboardHandler.Update();
            MouseHandler.Update();

            Map.Update(gameTime);
            Player.Update(gameTime);
            MovingObject.UpdateNonPlayer(gameTime);
            Time.Update(gameTime);

            base.Update(gameTime);
            
        }


        /// <summary>
        /// This is called when the game should draw itself.
        /// </summary>
        /// <param name="gameTime">Provides a snapshot of timing values.</param>
        protected override void Draw(GameTime gameTime)
        {
            GraphicsDevice.Clear(Color.Black);

            // TODO: Add your drawing code here
            spriteBatch.Begin(SpriteSortMode.FrontToBack, null, SamplerState.PointClamp, null, null, null, Matrix.CreateScale(SCALE));

            // draw current map
            Map.Draw(spriteBatch);
            Player.Draw(spriteBatch);
            MovingObject.DrawNonPlayer(spriteBatch);
            Time.Draw(spriteBatch);

            spriteBatch.End();


            base.Draw(gameTime);
        }
    }
}