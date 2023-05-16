using Microsoft.Xna.Framework;
using Microsoft.Xna.Framework.Graphics;
using Microsoft.Xna.Framework.Input;
using System;
using System.Threading;

namespace TicTacToe
{
    /// <summary>
    /// This is the main type for your game.
    /// </summary>
    public class Game1 : Game
    {
        private GraphicsDeviceManager graphics;
        private SpriteBatch spriteBatch;

        private Texture2D backGround, X, O, buttons;

        private SpriteFont textFont, boardFont;
        private Board board;
        private Player player;
        private const int GRID_SIZE = 68;
        private long lastTime = DateTime.Now.Ticks / TimeSpan.TicksPerMillisecond;
        private int milliDelay = 0;

        // data
        private int gameCount = 500; // 50000 max test
        private int currentGameCount;
        private int[] moveData; // moveNumber * spot
        private int[] moveTotal; // spot

        private bool showGame = true, pause = true, mReleased = false;
        private int buttonW = 56, buttonH = 26;

        public Game1()
        {
            graphics = new GraphicsDeviceManager(this);
            Content.RootDirectory = "Content";
            graphics.PreferredBackBufferWidth = 720;
            graphics.PreferredBackBufferHeight = 720;
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
            Window.Title = "TicTacToeness";
            IsMouseVisible = true;
            board = new Board();
            player = new Player();

            currentGameCount = 0;
            moveData = new int[board.getSize() * board.getSize()];
            moveTotal = new int[board.getSize()];
            for (int i = 0; i < board.getSize() * board.getSize(); i++)
            {
                moveData[i] = 0;
            }
            for (int i = 0; i < board.getSize(); i++)
            {
                moveTotal[i] = 0;
            }

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
            textFont = Content.Load<SpriteFont>("textFont");
            boardFont = Content.Load<SpriteFont>("boardFont");
            backGround = Content.Load<Texture2D>("backGround");
            O = Content.Load<Texture2D>("O");
            X = Content.Load<Texture2D>("X");
            buttons = Content.Load<Texture2D>("buttons");
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
            // 296 360
            // less
            if (Mouse.GetState().X >= 296 + 4 && Mouse.GetState().X <= 296 + 4 + buttonW && mReleased && //
                Mouse.GetState().Y >= 630 + 4 && Mouse.GetState().Y <= 630 + 4 + buttonH && Mouse.GetState().LeftButton == ButtonState.Pressed)
            {
                gameCount -= 100;
                if(gameCount < 0) gameCount = 0;
                mReleased = false;
            }
            // more
            if (Mouse.GetState().X >= 296 + 8 + buttonW && Mouse.GetState().X <= 296 + 8 + buttonW * 2 && mReleased && //
                Mouse.GetState().Y >= 630 + 4 && Mouse.GetState().Y <= 630 + 4 + buttonH && Mouse.GetState().LeftButton == ButtonState.Pressed)
            {
                gameCount += 100;
                mReleased = false;
            }
            // begin
            if (Mouse.GetState().X >= 296 + 4 && Mouse.GetState().X <= 296 + 4 + buttonW && mReleased && //
                Mouse.GetState().Y >= 630 + 8 + buttonH && Mouse.GetState().Y <= 630 + 8 + buttonH * 2 && Mouse.GetState().LeftButton == ButtonState.Pressed)
            {
                if (pause) 
                {
                    board.clear();
                    pause = false;
                    mReleased = false;
                    currentGameCount = 0;
                    for (int i = 0; i < board.getSize() * board.getSize(); i++)
                    {
                        moveData[i] = 0;
                    }
                    for (int i = 0; i < board.getSize(); i++)
                    {
                        moveTotal[i] = 0;
                    }
                }
            }
            // pause
            if (Mouse.GetState().X >= 296 + 8 + buttonW && Mouse.GetState().X <= 296 + 8 + buttonW * 2 && mReleased && //
                Mouse.GetState().Y >= 630 + 8 + buttonH && Mouse.GetState().Y <= 630 + 8 + buttonH * 2 && Mouse.GetState().LeftButton == ButtonState.Pressed)
            {
                pause = !pause;
                mReleased = false;
            }


            if (Mouse.GetState().LeftButton == ButtonState.Released)
            {
                mReleased = true;
            }

            if (!pause) 
            { 
                if (currentGameCount < gameCount) 
                {
                    long newTime = DateTime.Now.Ticks / TimeSpan.TicksPerMillisecond;
                    if (newTime - lastTime >= milliDelay)
                    {
                        lastTime = newTime;
                        // check win
                        char win = board.checkWin();
                        if (win != '_')
                        {
                            // get stats reset
                            currentGameCount++;
                            if (win != 'D')
                            {
                                int i = 0;
                                if (win == 'O') i = 1;
                                for (; i < board.getSize(); i += 2)
                                {
                                    if (board.getMoveSequence()[i] != -1)
                                    {
                                        // Console.WriteLine(i * board.getSize() + board.getMoveSequence()[i] + " = " + i + " * " + board.getSize() + " + " + board.getMoveSequence()[i]);
                                        moveData[i * board.getSize() + board.getMoveSequence()[i]]++;
                                    }
                                }
                            }
                            if (currentGameCount < gameCount - 1) board.clear();
                        }
                        else
                        {
                            // get move until success
                            int moveNumber = board.getMoveNumber();
                            int nextMoveNumber = moveNumber;
                            do
                            {
                                board.setMove(player.getMove(moveNumber, board.getSize()));
                                nextMoveNumber = board.getMoveNumber();
                            } while (moveNumber == nextMoveNumber);
                        }
                    }
                } }
            base.Update(gameTime);
        }

        /// <summary>
        /// This is called when the game should draw itself.
        /// </summary>
        /// <param name="gameTime">Provides a snapshot of timing values.</param>
        protected override void Draw(GameTime gameTime)
        {
            GraphicsDevice.Clear(Color.CornflowerBlue);

            // TODO: Add your drawing code here
            spriteBatch.Begin();

            spriteBatch.Draw(backGround, //
                new Vector2(0, 0), Color.White);

            spriteBatch.Draw(buttons, //
                new Vector2(296, 630), Color.White);

            spriteBatch.DrawString(textFont, "Game " + currentGameCount + "/" + gameCount, //
                new Vector2(35, 340), Color.White);
            if (currentGameCount >= gameCount || showGame)
            {
                // draw board
                for (int i = 0; i < Math.Sqrt(board.getSize()); i++)
                {
                    for (int j = 0; j < Math.Sqrt(board.getSize()); j++)
                    {
                        if (board.getBoard()[j + i * (int)Math.Sqrt(board.getSize())] != '_')
                        {
                            if (board.getBoard()[j + i * (int)Math.Sqrt(board.getSize())] == 'X')
                            {
                                spriteBatch.Draw(X, //
                                    new Vector2(40 + j * GRID_SIZE, 110 + i * GRID_SIZE), Color.White);
                            }
                            if (board.getBoard()[j + i * (int)Math.Sqrt(board.getSize())] == 'O')
                            {
                                spriteBatch.Draw(O, //
                                    new Vector2(40 + j * GRID_SIZE, 110 + i * GRID_SIZE), Color.White);
                            }
                        }
                    }
                }

                // draw stats
                spriteBatch.DrawString(textFont, "Move", //
                    new Vector2(35, 370), Color.White);
                for (int j = 0; j < board.getSize(); j++)
                {
                    spriteBatch.DrawString(textFont, "Spot " + (j + 1), //
                               new Vector2(20, 402 + j * 22), Color.White);
                }
                for (int i = 0; i < board.getSize(); i++)
                {
                    spriteBatch.DrawString(textFont, "" + (i + 1), //
                                   new Vector2(108 + i * 60, 370), Color.White);
                    for (int j = 0; j < board.getSize(); j++)
                    {
                        spriteBatch.DrawString(textFont, "" + moveData[j + i * board.getSize()], //
                                new Vector2(108 + i * 60, 402 + j * 22), Color.White);
                    }
                }

                spriteBatch.DrawString(textFont, "Total", //
                            new Vector2(108 + 9 * 60, 370), Color.White);
                for (int j = 0; j < board.getSize(); j++)
                    {
                    int totalTemp = 0;
                    for (int i = 0; i < board.getSize(); i++)
                        {
                        totalTemp += moveData[j + i * board.getSize()];
                    }
                    moveTotal[j] = totalTemp;
                    spriteBatch.DrawString(textFont, "" + moveTotal[j], //
                                new Vector2(108 + 9 * 60, 402 + j * 22), Color.White);
                }

                // get stats
                int[] move = new int[board.getSize()];
                for (int i = 0; i < board.getSize(); i++)
                {
                    int tempLarge = 0;
                    for (int j = 0; j < board.getSize(); j++)
                    {
                        if (tempLarge <= moveData[j + i * board.getSize()])
                        {
                            move[i] = j;
                            tempLarge = moveData[j + i * board.getSize()];
                        }
                    }
                }
                spriteBatch.DrawString(textFont, "Most Successful Moves", //
                    new Vector2(320, 100), Color.White);
                spriteBatch.DrawString(textFont, "X           O           SPOT", //
                    new Vector2(420, 130), Color.White);
                for (int i = 0; i < board.getSize(); i++)
                {
                    string offSet = "    ";
                    if (i % 2 != 0) offSet = "                 ";
                    spriteBatch.DrawString(textFont, "Move " + (i + 1) + offSet + (move[i] + 1), //
                        new Vector2(320, 160 + i * 20), Color.White);
                }
                int lastMax = 999999999;
                int max;
                int[] order = new int[] { 0,1,2,3,4,5,6,7,8};
                for (int i = 0; i < board.getSize(); i++)
                {
                    max = 0;
                    for (int j = 0; j < board.getSize(); j++)
                    {
                        if (max <= moveTotal[j] && moveTotal[j] < lastMax)
                        {
                            max = moveTotal[j];
                            order[i] = j;
                        }
                    }
                    lastMax = moveTotal[order[i]];
                }
                for (int i = 0; i < board.getSize(); i++)
                {
                    spriteBatch.DrawString(textFont, "" + (order[i]+1), //
                        new Vector2(610, 160 + i * 20), Color.White);
                }

            }
            spriteBatch.End();

            base.Draw(gameTime);
        }
    }

    // board class
    public class Board
    {
        const int SIZE = 9;
        int moveNumber;
        private char[] board = new char[SIZE];
        private int[] moveSequence = new int[SIZE];

        public Board()
        {
            clear();
        }

        public void setMove(int spot)
        {
            if (board[spot] == '_')
            {
                if (moveNumber % 2 == 0)
                {
                    board[spot] = 'X';
                }
                if (moveNumber % 2 != 0)
                {
                    board[spot] = 'O';
                }
                moveSequence[moveNumber] = spot;
                moveNumber++;
            }
        }

        public int getSize()
        {
            return SIZE;
        }

        public int getMoveNumber()
        {
            return moveNumber;
        }

        public char[] getBoard()
        {
            return board;
        }

        public int[] getMoveSequence()
        {
            return moveSequence;
        }

        public void clear()
        {
            moveNumber = 0;
            for (int i = 0; i < SIZE; i++)
            {
                board[i] = '_';
                moveSequence[i] = -1;
            }
        }

        public char checkWin() // returns winning symbol, '_' for not finished, 'D' for draw
        {
            char symb = 'X';
            for (int s = 0; s < 2; s++) // check each symbol
            {
                for (int i = 0; i < Math.Sqrt(SIZE); i++) // check rows
                {
                    if (board[0 + i * (int)Math.Sqrt(SIZE)] == symb && //
                        board[1 + i * (int)Math.Sqrt(SIZE)] == symb && //
                        board[2 + i * (int)Math.Sqrt(SIZE)] == symb)
                    {
                        return symb;
                    }
                }
                for (int i = 0; i < Math.Sqrt(SIZE); i++) // check columns
                {
                    if (board[i + 0 * (int)Math.Sqrt(SIZE)] == symb && //
                        board[i + 1 * (int)Math.Sqrt(SIZE)] == symb && //
                        board[i + 2 * (int)Math.Sqrt(SIZE)] == symb)
                    {
                        return symb;
                    }
                }
                // check diagonal
                if (board[0 + 0 * (int)Math.Sqrt(SIZE)] == symb && //
                       board[1 + 1 * (int)Math.Sqrt(SIZE)] == symb && //
                       board[2 + 2 * (int)Math.Sqrt(SIZE)] == symb)
                {
                    return symb;
                }
                if (board[2 + 0 * (int)Math.Sqrt(SIZE)] == symb && //
                       board[1 + 1 * (int)Math.Sqrt(SIZE)] == symb && //
                       board[0 + 2 * (int)Math.Sqrt(SIZE)] == symb)
                {
                    return symb;
                }
                symb = 'O';
            }
            // draw
            if (moveNumber == 9)
            {
                return 'D';
            }
            return '_';
        }

    }

    // player class
    public class Player
    {

        public Player()
        {

        }

        public int getMove(int moveNumber, int size)
        {
            Random rand = new Random();
            return rand.Next(0, size);
        }
    }
}
