using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using Microsoft.Xna.Framework;
using Microsoft.Xna.Framework.Content;
using Microsoft.Xna.Framework.Graphics;
using Microsoft.Xna.Framework.Input;

namespace vet
{
    class MovingObject
    {

        public static MovingObject[] interMapObjects;

        // temp
        public int lastMap;
        public Vector2 lastMapOffest;
        //


        protected string name;

        public Vector2 spritePos;
        public Vector2 standPos;

        public Vector2 spriteSize;
        public Vector2 standSize;
        public Vector2 spriteFromStandOffset;

        public Vector2 spriteSizeAlt;
        public Vector2 spriteFromStandOffsetAlt;

        public string className = "movingObject";

        protected int atMap;

        /*
         * 0 front stand
         * 1 back stand
         * 2 left stand
         * 3 right stand
         * 4 front walk
         * 5 back walk
         * 6 left walk
         * 7 right walk
        */

        protected int currentAnim = 0;
        protected Animation[] anims;

        protected int facingDir = 0;
        protected bool moving = false, running = false, sitting = false, sleeping = false;

        protected int moveAnimSpeed, standAnimSpeed, runAnimSpeed, moveSpeed;


        public static void InitNonPlayers()
        {
            interMapObjects = new MovingObject[3];
            interMapObjects[0] = new LiHe();
            interMapObjects[1] = new ZuoQing();
            interMapObjects[2] = new Dog("dog0");
        }

        public static void UpdateNonPlayer(GameTime gameTime)
        {
            for (int i = 0; i < interMapObjects.Length; i++)
            {
                interMapObjects[i].update(gameTime);
            }
        }

        public static void DrawNonPlayer(SpriteBatch spriteBatch)
        {
            for (int i = 0; i < interMapObjects.Length; i++)
            {
                interMapObjects[i].draw(spriteBatch);
            }
        }

        public virtual void update(GameTime gameTime)
        {

        }

        public virtual void draw(SpriteBatch spriteBatch)
        {
            if (atMap == Player.player.atMap)
            {
                float depth = (standPos.Y + standSize.Y / 2) / (Map.Height(atMap) * Map.TILE_HEIGHT);
                anims[currentAnim].draw(spriteBatch, spritePos, 0f, depth);
            }
        }

        protected void switchMap()
        {
            Rectangle standArea = new Rectangle((int)standPos.X, (int)standPos.Y, (int)standSize.X, (int)standSize.Y);
            for (int i = 0; i < Map.GetSwitchers(atMap).Length; i++)
            {
                if (standArea.Intersects(Map.GetSwitchers(atMap)[i].getRect()))
                {
                    Map.GetSwitchers(atMap)[i].action(this);
                }
            }
        }

        public virtual void move()
        {

        }

        public bool collideWithMap(int xOff, int yOff)
        {
            Rectangle r = new Rectangle();
            r.X = (int)standPos.X + xOff;
            r.Y = (int)standPos.Y + yOff;
            r.Width = (int)standSize.X;
            r.Height = (int)standSize.Y;

            // check object collide
            if(CollideWithInterMapObject(r, this)) return true;

            return Map.collideWithMap(atMap, r, false);
        }

        public bool collideWithTiles(int xOff, int yOff)
        {
            Color[] c = new Color[] { Color.Black };
            if (yOff == 0) // test X
            {
                c = new Color[(int)(standSize.X / Map.TILE_WIDTH) + 2];
                if (xOff == 1) // right
                {
                    for (int i = 0; i < c.Length - 1; i++)
                    {
                        if (((int)((standPos.X + standSize.X + 1) / Map.TILE_WIDTH) + (int)((standPos.Y + i * Map.TILE_HEIGHT) / Map.TILE_HEIGHT) * Map.Width(atMap)) < 0 || ((int)((standPos.X + standSize.X + 1) / Map.TILE_WIDTH) + (int)((standPos.Y + i * Map.TILE_HEIGHT) / Map.TILE_HEIGHT) * Map.Width(atMap)) > Map.Height(atMap) * Map.Width(atMap)) return true;
                        c[i] = Map.GetPassibleGrid(atMap)[(int)((standPos.X + standSize.X + 1) / Map.TILE_WIDTH) + (int)((standPos.Y + i * Map.TILE_HEIGHT) / Map.TILE_HEIGHT) * Map.Width(atMap)];
                    }
                    if (((int)((standPos.X + standSize.X + 1) / Map.TILE_WIDTH) + (int)((standPos.Y + standSize.Y - 1) / Map.TILE_HEIGHT) * Map.Width(atMap)) < 0 || ((int)((standPos.X + standSize.X + 1) / Map.TILE_WIDTH) + (int)((standPos.Y + standSize.Y - 1) / Map.TILE_HEIGHT) * Map.Width(atMap)) > Map.Height(atMap) * Map.Width(atMap)) return true;
                    c[c.Length - 1] = Map.GetPassibleGrid(atMap)[(int)((standPos.X + standSize.X + 1) / Map.TILE_WIDTH) + (int)((standPos.Y + standSize.Y - 1) / Map.TILE_HEIGHT) * Map.Width(atMap)];
                }
                else if (xOff == -1) // left
                {
                    for (int i = 0; i < c.Length - 1; i++)
                    {
                        if (((int)((standPos.X - 1) / Map.TILE_WIDTH) + (int)((standPos.Y + i * Map.TILE_HEIGHT) / Map.TILE_HEIGHT) * Map.Width(atMap)) < 0 || ((int)((standPos.X - 1) / Map.TILE_WIDTH) + (int)((standPos.Y + i * Map.TILE_HEIGHT) / Map.TILE_HEIGHT) * Map.Width(atMap)) > Map.Height(atMap) * Map.Width(atMap)) return true;
                        c[i] = Map.GetPassibleGrid(atMap)[(int)((standPos.X - 1) / Map.TILE_WIDTH) + (int)((standPos.Y + i * Map.TILE_HEIGHT) / Map.TILE_HEIGHT) * Map.Width(atMap)];
                    }
                    if (((int)((standPos.X - 1) / Map.TILE_WIDTH) + (int)((standPos.Y + standSize.Y - 1) / Map.TILE_HEIGHT) * Map.Width(atMap)) < 0 || ((int)((standPos.X - 1) / Map.TILE_WIDTH) + (int)((standPos.Y + standSize.Y - 1) / Map.TILE_HEIGHT) * Map.Width(atMap)) > Map.Height(atMap) * Map.Width(atMap)) return true;
                    c[c.Length - 1] = Map.GetPassibleGrid(atMap)[(int)((standPos.X - 1) / Map.TILE_WIDTH) + (int)((standPos.Y + standSize.Y - 1) / Map.TILE_HEIGHT) * Map.Width(atMap)];
                }
            }
            else if (xOff == 0) // test Y
            {
                c = new Color[(int)(standSize.Y / Map.TILE_HEIGHT) + 2];
                if (yOff == 1) // down
                {
                    for (int i = 0; i < c.Length - 1; i++)
                    {
                        if (((int)((standPos.X + i * Map.TILE_WIDTH) / Map.TILE_WIDTH) + (int)((standPos.Y + standSize.Y + 1) / Map.TILE_HEIGHT) * Map.Width(atMap)) < 0 || ((int)((standPos.X + i * Map.TILE_WIDTH) / Map.TILE_WIDTH) + (int)((standPos.Y + standSize.Y + 1) / Map.TILE_HEIGHT) * Map.Width(atMap)) > Map.Height(atMap) * Map.Width(atMap)) return true;
                        c[i] = Map.GetPassibleGrid(atMap)[(int)((standPos.X + i * Map.TILE_WIDTH) / Map.TILE_WIDTH) + (int)((standPos.Y + standSize.Y + 1) / Map.TILE_HEIGHT) * Map.Width(atMap)];
                    }
                    if (((int)((standPos.X + standSize.X - 1) / Map.TILE_WIDTH) + (int)((standPos.Y + standSize.Y + 1) / Map.TILE_HEIGHT) * Map.Width(atMap)) < 0 || ((int)((standPos.X + standSize.X - 1) / Map.TILE_WIDTH) + (int)((standPos.Y + standSize.Y + 1) / Map.TILE_HEIGHT) * Map.Width(atMap)) > Map.Height(atMap) * Map.Width(atMap)) return true;
                    c[c.Length - 1] = Map.GetPassibleGrid(atMap)[(int)((standPos.X + standSize.X - 1) / Map.TILE_WIDTH) + (int)((standPos.Y + standSize.Y + 1) / Map.TILE_HEIGHT) * Map.Width(atMap)];
                }
                else if (yOff == -1) // up
                {
                    for (int i = 0; i < c.Length - 1; i++)
                    {
                        if (((int)((standPos.X + i * Map.TILE_WIDTH) / Map.TILE_WIDTH) + (int)((standPos.Y - 1) / Map.TILE_HEIGHT) * Map.Width(atMap)) < 0 || ((int)((standPos.X + i * Map.TILE_WIDTH) / Map.TILE_WIDTH) + (int)((standPos.Y - 1) / Map.TILE_HEIGHT) * Map.Width(atMap)) > Map.Height(atMap) * Map.Width(atMap)) return true;
                        c[i] = Map.GetPassibleGrid(atMap)[(int)((standPos.X + i * Map.TILE_WIDTH) / Map.TILE_WIDTH) + (int)((standPos.Y - 1) / Map.TILE_HEIGHT) * Map.Width(atMap)];
                    }
                    if (((int)((standPos.X + standSize.X - 1) / Map.TILE_WIDTH) + (int)((standPos.Y - 1) / Map.TILE_HEIGHT) * Map.Width(atMap)) < 0 || ((int)((standPos.X + standSize.X - 1) / Map.TILE_WIDTH) + (int)((standPos.Y - 1) / Map.TILE_HEIGHT) * Map.Width(atMap)) > Map.Height(atMap) * Map.Width(atMap)) return true;
                    c[c.Length - 1] = Map.GetPassibleGrid(atMap)[(int)((standPos.X + standSize.X - 1) / Map.TILE_WIDTH) + (int)((standPos.Y - 1) / Map.TILE_HEIGHT) * Map.Width(atMap)];
                }
            }


            for (int i = 0; i < c.Length; i++)
            {
                if (c[i].R == 0 && c[i].G == 0 && c[i].B == 0)
                {
                    return true;
                }
            }
            return false;
        }

        public Vector2 getstandPos()
        {
            return standPos;
        }

        public void setstandPos(Vector2 pos)
        {
            standPos = pos;
        }

        public int getAtMap()
        {
            return atMap;
        }

        public void setAtMap(int i)
        {
            atMap = i;
        }

        public static bool CollideWithInterMapObject(Rectangle r, MovingObject o)
        {
            if (o.getAtMap() == Player.player.getAtMap() &&
                !(Player.player.getCollideRect().X == o.getCollideRect().X &&
                       Player.player.getCollideRect().Y == o.getCollideRect().Y &&
                       Player.player.getCollideRect().Width == o.getCollideRect().Width &&
                       Player.player.getCollideRect().Height == o.getCollideRect().Height) &&
                       Player.player.getCollideRect().Intersects(r))
                return true;
            for (int i = 0; i < interMapObjects.Length; i++)
            {
                if (o.getAtMap() == interMapObjects[i].getAtMap() &&
                    !(interMapObjects[i].getCollideRect().X == o.getCollideRect().X &&
                    interMapObjects[i].getCollideRect().Y == o.getCollideRect().Y &&
                    interMapObjects[i].getCollideRect().Width == o.getCollideRect().Width && 
                    interMapObjects[i].getCollideRect().Height == o.getCollideRect().Height) &&
                    interMapObjects[i].getCollideRect().Intersects(r))
                return true;
            }
            return false;
        }

        public virtual Rectangle getCollideRect()
        {
            Rectangle col = new Rectangle(0, 0, (int)standSize.X, (int)standSize.Y);
            col.X += (int) standPos.X;
            col.Y += (int) standPos.Y;
            return col;
        }

        protected bool clicked()
        {
            if (facingDir == 3 || facingDir == 2)
            {
                if (!Player.usingWindow && //
                    MouseHandler.isClickedL &&
                    new Rectangle((int)(spritePos.X - Camera.GetOffset().X), (int)(spritePos.Y - Camera.GetOffset().Y), (int)spriteSize.X, (int)spriteSize.Y).Contains(MouseHandler.Pos))
                {
                    return true;
                }
            }
            else if (facingDir == 1 || facingDir == 0)
            {
                if (!Player.usingWindow && //
                    MouseHandler.isClickedL &&
                    new Rectangle((int)(spritePos.X - Camera.GetOffset().X), (int)(spritePos.Y - Camera.GetOffset().Y), (int)spriteSizeAlt.X, (int)spriteSizeAlt.Y).Contains(MouseHandler.Pos))
                {
                    return true;
                }
            }
            return false;
        }

        protected virtual void updateClicked(GameTime gameTime)
        {

        }

        protected static MovingObject[] getInterMapObjects()
        {
            return interMapObjects;
        }
    }
}