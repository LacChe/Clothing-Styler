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
    class ChestInventory : Inventory
    {

        private bool open = false;

        private int w = 9, h = 7;
        private int page1OffsetX = 100, page1OffsetY = 32, page2OffsetX = 128, page2OffsetY = 32;

        private Button exit;

        public ChestInventory(string n) :  base(n)
        {
            size.X = 776;
            size.Y = 544;
            pos.X = (Game1.frameSize.X - 616) / 2 + 616 / 2 - size.X / 2;
            pos.Y = (Game1.frameSize.Y - 72) - 16 - size.Y - 32 - 32;
            name = n;
            maxAmount = w * h;
            currentAmount = 0;
            items = new Item[maxAmount];

            for (int i = 0; i < maxAmount; i++)
            {
                items[i] = null;
            }

            // set buttons
            Rectangle rE = new Rectangle((int)pos.X + 38, (int)pos.Y + 80, 32, 32);
            exit = new Button(("exitChestInventory"), rE);

            inventorySlotButtons = new Button[maxAmount];
            for (int j = 0; j < h; j++)
            {
                for (int i = 0; i < w; i++)
                {
                    Rectangle r = new Rectangle((int)pos.X + 4 + i * (gridSize + 4), (int)pos.Y + 4 + j * (gridSize + 4), gridSize, gridSize);
                    if (i <= 3)
                    {
                        r.X += page1OffsetX;
                        r.Y += page1OffsetY;
                    }
                    else if(i > 3)
                    {
                        r.X += page2OffsetX;
                        r.Y += page2OffsetY;
                    }
                    inventorySlotButtons[i + j * w] = new Button(("chestInventory" + (i + j * w)), r);
                }
            }
        }

        public void activate()
        {
            if (open && Player.usingWindow == true)
            {
                open = false;
                Player.usingWindow = false;
            }
            else if (!open && Player.usingWindow == false)
            {
                open = true;
                Player.usingWindow = true;
            }
        }

        public bool opened()
        {
            return open;
        }

        public override void update(GameTime gameTime)
        {
            exit.update(gameTime, this);

            for (int i = 0; i < maxAmount; i++)
            {
                bool itemPut = false;
                string buttonOut = "";
                buttonOut = inventorySlotButtons[i].update(gameTime, this);

                if (buttonOut != "" && buttonOut != "endFunc")
                {
                    // put item
                    if ((items[i] != null && Player.player.inventory.getItem(Player.player.inventory.getIndex()) != null && //
                        items[i].getName() == Player.player.inventory.getItem(Player.player.inventory.getIndex()).getName()))
                    {
                        if (MouseHandler.isClickedL && !MouseHandler.isClickedR) {
                            Item tempItem = Player.player.inventory.removeItem(Player.player.inventory.getIndex());
                            if (tempItem != null)
                            {
                                items[i].setCount(items[i].getCount() + tempItem.getCount());
                            } 
                        }
                        if (!MouseHandler.isClickedL && MouseHandler.isClickedR) {
                            Item tempItem = Player.player.inventory.removeSingleItem(Player.player.inventory.getIndex());
                            if (tempItem != null)
                            {
                                items[i].setCount(items[i].getCount() + 1);
                            }
                        }
                        itemPut = true;
                    }
                    if ((items[i] == null && Player.player.inventory.getItem(Player.player.inventory.getIndex()) != null))
                    {
                        if (MouseHandler.isClickedL && !MouseHandler.isClickedR)
                            items[i] = Player.player.inventory.removeItem(Player.player.inventory.getIndex());
                        if (!MouseHandler.isClickedL && MouseHandler.isClickedR)
                        {
                            Item itemTemp = Player.player.inventory.removeSingleItem(Player.player.inventory.getIndex());
                            if (itemTemp != null)
                            {
                                items[i] = itemTemp;
                            }
                        }
                        itemPut = true;
                    }
                    // get item
                    if (!itemPut)
                    {
                        if (Player.player.inventory.addItem(items[i]))
                        {
                            items[i] = null;
                        }
                    }
                }
            }
        }

        public override void draw(SpriteBatch spriteBatch)
        {
            if (show)
            {
                Camera.DrawUI(spriteBatch, SpriteSheet.GetSheet("chestInventory"), pos, new Rectangle(0, 0, (int)size.X, (int)size.Y), Color.White, 0f, 1.0f, false, 0.9999990f, 1.0f);


                for (int j = 0; j < h; j++)
                {
                    for (int i = 0; i < w; i++)
                    {
                        if (items[i + j * w] != null)
                        {
                            // get scale
                            float length = 0;
                            float scale = 1.0f;
                            bool longerWidth = false;
                            if (items[i + j * w].getSpriteSrcRect().Width > items[i + j * w].getSpriteSrcRect().Height)
                            {
                                longerWidth = true;
                            }
                            if (longerWidth)
                            {
                                length = items[i + j * w].getSpriteSrcRect().Width;
                            }
                            else
                            {
                                length = items[i + j * w].getSpriteSrcRect().Height;
                            }
                            // scale = 1.0f / (gridSize / length);
                            scale = 1.0f / (length / gridSize);

                            // get pos
                            Vector2 itemPos = pos;

                            itemPos.Y += (4 + j * (gridSize + 4));
                            itemPos.X += (4 + i * (gridSize + 4));

                            if (i <= 3)
                            {
                                itemPos.X += page1OffsetX;
                                itemPos.Y += page1OffsetY;
                            }
                            else if (i > 3)
                            {
                                itemPos.X += page2OffsetX;
                                itemPos.Y += page2OffsetY;
                            }

                            if (items[i + j * w].isStackable()) {
                                // Camera.DrawUIString(spriteBatch, SpriteSheet.GetFont("inventoryFont"), "" + items[i + j * w].getCount(), itemPos, Color.Black, 0f, 1.0f, false, 0.9999995f, 0.75f);
                                Camera.DrawUIString(spriteBatch, SpriteSheet.GetFont("inventoryFont"), "" + items[i + j * w].getCount(), itemPos, Color.Black, 0f, 1.0f, false, 0.9999995f, 0.75f);
                            }

                            itemPos.Y += (gridSize - items[i + j * w].getSpriteSrcRect().Height * scale) / 2;
                            itemPos.X += (gridSize - items[i + j * w].getSpriteSrcRect().Width * scale) / 2;

                            Camera.DrawUI(spriteBatch, SpriteSheet.GetSheet(items[i + j * w].getSheetName()), itemPos, items[i + j * w].getSpriteSrcRect(), Color.White, 0f, scale, false, 0.9999994f, 1.0f);
                        }
                    }
                }
            }
        }
    }
}