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
    class Player : Character
    {
        public static Player player = new Player();

        public static bool usingWindow = false;
        private int playerReach = 64 * 5;

        public Inventory inventory;

        public static void Init()
        {
            player.className = "player";
            player.name = "player";
            player.inventory = new Inventory("playerInventory");

            player.atMap = 3;

            player.moveAnimSpeed = 10; 
            player.standAnimSpeed = 10; 
            player.moveSpeed = 5;

            player.spriteSize = new Vector2(64, 128);
            player.standSize = new Vector2(24, 12);
            player.spriteFromStandOffset = new Vector2(20, 114);

            player.spriteSizeAlt = player.spriteSize;
            player.spriteFromStandOffsetAlt = player.spriteFromStandOffset;

            player.standPos = new Vector2(3300, 2600);
            player.standPos = new Vector2(2700, 300);

            // anims
            Rectangle frontStandBlink = new Rectangle(0 * (int)player.spriteSize.X, 0 * (int)player.spriteSize.Y, (int)player.spriteSize.X, (int)player.spriteSize.Y);
            Rectangle frontStand1 = new Rectangle(1 * (int)player.spriteSize.X, 0 * (int)player.spriteSize.Y, (int)player.spriteSize.X, (int)player.spriteSize.Y);

            Rectangle backStand0 = new Rectangle(0 * (int)player.spriteSize.X, 2 * (int)player.spriteSize.Y, (int)player.spriteSize.X, (int)player.spriteSize.Y);

            Rectangle sideBlink = new Rectangle(0 * (int)player.spriteSize.X, 1 * (int)player.spriteSize.Y, (int)player.spriteSize.X, (int)player.spriteSize.Y);
            Rectangle sideStand1 = new Rectangle(1 * (int)player.spriteSize.X, 1 * (int)player.spriteSize.Y, (int)player.spriteSize.X, (int)player.spriteSize.Y);

            Rectangle frontWalk0 = new Rectangle(1 * (int)player.spriteSize.X, 0 * (int)player.spriteSize.Y, (int)player.spriteSize.X, (int)player.spriteSize.Y);
            Rectangle frontWalk1 = new Rectangle(2 * (int)player.spriteSize.X, 0 * (int)player.spriteSize.Y, (int)player.spriteSize.X, (int)player.spriteSize.Y);
            Rectangle frontWalk2 = new Rectangle(3 * (int)player.spriteSize.X, 0 * (int)player.spriteSize.Y, (int)player.spriteSize.X, (int)player.spriteSize.Y);

            Rectangle backWalk0 = new Rectangle(0 * (int)player.spriteSize.X, 2 * (int)player.spriteSize.Y, (int)player.spriteSize.X, (int)player.spriteSize.Y);
            Rectangle backWalk1 = new Rectangle(1 * (int)player.spriteSize.X, 2 * (int)player.spriteSize.Y, (int)player.spriteSize.X, (int)player.spriteSize.Y);
            Rectangle backWalk2 = new Rectangle(2 * (int)player.spriteSize.X, 2 * (int)player.spriteSize.Y, (int)player.spriteSize.X, (int)player.spriteSize.Y);

            Rectangle sideWalk0 = new Rectangle(1 * (int)player.spriteSize.X, 1 * (int)player.spriteSize.Y, (int)player.spriteSize.X, (int)player.spriteSize.Y);
            Rectangle sideWalk1 = new Rectangle(2 * (int)player.spriteSize.X, 1 * (int)player.spriteSize.Y, (int)player.spriteSize.X, (int)player.spriteSize.Y);
            Rectangle sideWalk2 = new Rectangle(3 * (int)player.spriteSize.X, 1 * (int)player.spriteSize.Y, (int)player.spriteSize.X, (int)player.spriteSize.Y);

            player.anims = new Animation[8];
            player.anims[0] = new Animation("player0", 10, new Rectangle[] { //
                frontStandBlink ,frontStand1, frontStand1, frontStand1, frontStand1, frontStand1, frontStand1, frontStand1, frontStand1, frontStand1}, player.standAnimSpeed);
            player.anims[1] = new Animation("player0", 1, new Rectangle[] { //
                backStand0}, 10);
            player.anims[2] = new Animation("player0", 10, new Rectangle[] { //
                sideBlink ,sideStand1, sideStand1, sideStand1, sideStand1, sideStand1, sideStand1, sideStand1, sideStand1, sideStand1}, player.standAnimSpeed);
            player.anims[3] = new Animation("player0", 10, new Rectangle[] { //
                sideBlink ,sideStand1, sideStand1, sideStand1, sideStand1, sideStand1, sideStand1, sideStand1, sideStand1, sideStand1}, player.standAnimSpeed);
            player.anims[3].setHorizontalFlip();

            player.anims[4] = new Animation("player0", 4, new Rectangle[] { //
                frontWalk0 ,frontWalk1, frontWalk0, frontWalk2}, player.moveAnimSpeed);
            player.anims[5] = new Animation("player0", 4, new Rectangle[] { //
                backWalk0 ,backWalk1, backWalk0, backWalk2}, player.moveAnimSpeed);
            player.anims[6] = new Animation("player0", 4, new Rectangle[] { //
                sideWalk0 ,sideWalk1, sideWalk0, sideWalk2}, player.moveAnimSpeed);
            player.anims[7] = new Animation("player0", 4, new Rectangle[] { //
                sideWalk0 ,sideWalk1, sideWalk0, sideWalk2}, player.moveAnimSpeed);
            player.anims[7].setHorizontalFlip();
        }

        public static void Update(GameTime gameTime)
        {
            if (!usingWindow)
            {
                player.update(gameTime);
            }
            player.inventory.update(gameTime);
        }

        public override void update(GameTime gameTime)
        {

            moving = false;
            if (Keyboard.GetState().IsKeyDown(Keys.W) || Keyboard.GetState().IsKeyDown(Keys.A) || Keyboard.GetState().IsKeyDown(Keys.S) || Keyboard.GetState().IsKeyDown(Keys.D))
            {
                moving = true;
            }

            move();

            if (facingDir == 3 || facingDir == 2)
            {
                spritePos.X = standPos.X - spriteFromStandOffset.X;
                spritePos.Y = standPos.Y - spriteFromStandOffset.Y;
            }
            else if (facingDir == 1 || facingDir == 0)
            {
                spritePos.X = standPos.X - spriteFromStandOffsetAlt.X;
                spritePos.Y = standPos.Y - spriteFromStandOffsetAlt.Y;
            }

            // right click get item
            bool gotItem = false;
            if (MouseHandler.isClickedR)
            {
                for(int i = 0; i < Map.GetItems(atMap).Count; i++)
                {
                    if (((Item)(Map.GetItems(atMap)[i])).playerInteractable && !((Item)(Map.GetItems(atMap)[i])).isSmallItem())
                    {
                        Rectangle itemRect = ((Item)Map.GetItems(atMap)[i]).getCollideRect();
                        if (itemRect.Contains(MouseHandler.Pos + Camera.GetOffset()))
                        {
                            inventory.addItem(Map.RemoveItem(atMap, MouseHandler.Pos + Camera.GetOffset()));
                            gotItem = true;
                            i--;
                        } 
                    }
                }
            }

            // place inventory item if didnt get Item
            if (!gotItem && MouseHandler.isClickedR && inventory.getItem(inventory.getIndex()) != null)
            {
                if (inventory.getItem(inventory.getIndex()).GetType().Name == "Furniture" || inventory.getItem(inventory.getIndex()).GetType().Name == "Chest")
                {
                    Vector2 clickPos = MouseHandler.Pos + Camera.GetOffset();
                    Vector2 itemDropPos = clickPos;
                    itemDropPos.X -= clickPos.X % Map.TILE_WIDTH;
                    itemDropPos.Y -= clickPos.Y % Map.TILE_HEIGHT;

                    // check map collide
                    if (!Map.collideWithMap(atMap, new Rectangle(
                        (int)itemDropPos.X,
                        (int)itemDropPos.Y,
                        inventory.getItem(inventory.getIndex()).getCollideRect().Width,
                        inventory.getItem(inventory.getIndex()).getCollideRect().Height), true) &&
                        // check object collide
                        !CollideWithInterMapObject(new Rectangle(
                        (int)itemDropPos.X,
                        (int)itemDropPos.Y,
                        inventory.getItem(inventory.getIndex()).getSpriteSrcRect().Width,
                        inventory.getItem(inventory.getIndex()).getSpriteSrcRect().Height), this))
                    {
                        inventory.getItem(inventory.getIndex()).setMapPos(itemDropPos);
                        Map.AddItem(atMap, inventory.removeItem(inventory.getIndex()));
                    }
                }
                else
                {
                    Vector2 clickPos = MouseHandler.Pos + Camera.GetOffset();
                    // if click self
                    if (new Rectangle((int)spritePos.X, (int)spritePos.Y, (int)spriteSize.X, (int)spriteSize.Y).Contains(clickPos))
                    {
                        inventory.getItem(inventory.getIndex()).activateOnPlayer();
                    }
                    else 
                    { 
                        if (Vector2.Distance(clickPos, new Vector2(standPos.X + standSize.X / 2, standPos.Y + standSize.Y / 2)) < playerReach)
                        {
                            Vector2 itemDropPos = clickPos;
                            itemDropPos.X -= inventory.getItem(inventory.getIndex()).getSpriteSrcRect().Width / 2;
                            itemDropPos.Y -= inventory.getItem(inventory.getIndex()).getSpriteSrcRect().Height / 2;

                            if (!Map.collideWithMap(atMap, new Rectangle(
                                // check map collide
                                (int)itemDropPos.X,
                                (int)itemDropPos.Y,
                                inventory.getItem(inventory.getIndex()).getSpriteSrcRect().Width,
                                inventory.getItem(inventory.getIndex()).getSpriteSrcRect().Height), true) &&
                                // check object collide
                                !CollideWithInterMapObject(new Rectangle(
                                (int)itemDropPos.X,
                                (int)itemDropPos.Y,
                                inventory.getItem(inventory.getIndex()).getSpriteSrcRect().Width,
                                inventory.getItem(inventory.getIndex()).getSpriteSrcRect().Height), this))
                            {
                                inventory.getItem(inventory.getIndex()).setMapPos(itemDropPos);
                                if (MouseHandler.isClickedL && !MouseHandler.isClickedR)
                                    Map.AddItem(atMap, inventory.removeItem(inventory.getIndex()));
                                if (!MouseHandler.isClickedL && MouseHandler.isClickedR)
                                {
                                    Item itemTemp = inventory.removeSingleItem(inventory.getIndex()); 
                                    if (itemTemp != null)
                                    {
                                        Map.AddItem(atMap, itemTemp);
                                    }
                                }
                            }
                        }
                    }
                }
            }

            currentAnim = facingDir;
            if (moving) currentAnim += 4;

            anims[currentAnim].update(gameTime);

        }

        public static void Draw(SpriteBatch spriteBatch)
        {
            player.draw(spriteBatch);
            player.inventory.draw(spriteBatch);
        }

        public override void move()
        {
            // onion move
            int nextX = 0;
            int nextY = 0;
            if (Keyboard.GetState().IsKeyDown(Keys.W))
            {
                facingDir = 1;
                nextY -= 1;
                if (nextY < -1) nextY = -1;
            }
            if (Keyboard.GetState().IsKeyDown(Keys.S))
            {
                facingDir = 0;
                nextY += 1;
                if (nextY > 1) nextY = 1;
            }
            if (Keyboard.GetState().IsKeyDown(Keys.A))
            {
                facingDir = 3;
                nextX -= 1;
                if (nextX < -1) nextX = -1;
            }
            if (Keyboard.GetState().IsKeyDown(Keys.D))
            {
                facingDir = 2;
                nextX += 1;
                if (nextX > 1) nextX = 1;
            }
            int oldSpeed = moveSpeed;
            if (Keyboard.GetState().IsKeyDown(Keys.LeftShift))
            {
                moveSpeed *= 10;
            }

            for (int ms = 0; ms < moveSpeed; ms++)
            {
                // tiles
                // x
                if (!collideWithMap(nextX, 0))
                {
                    standPos.X += nextX;
                }
                // y
                if (!collideWithMap(0, nextY))
                {
                    standPos.Y += nextY;
                }

                // TODO: stuff that needs to hapen every pixel step
                switchMap();

                // get small items
                if (inventory.autoPickup) {
                    for (int i = 0; i < Map.GetItems(atMap).Count; i++)
                    {
                        Rectangle itemRect = ((Item)Map.GetItems(atMap)[i]).getCollideRect();
                        if (new Rectangle((int)standPos.X, (int)standPos.Y, (int)standSize.X, (int)standSize.Y).Intersects(itemRect))
                        {
                            inventory.addItem(Map.RemoveItem(atMap, new Rectangle((int)standPos.X, (int)standPos.Y, (int)standSize.X, (int)standSize.Y)));
                            i--;
                        }
                    }
                }
            }
            moveSpeed = oldSpeed;
        }

    }
}