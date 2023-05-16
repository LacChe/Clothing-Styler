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
    class Inventory : InGameWindow
    {
        protected string name;
        protected int maxAmount, index, currentAmount, gridSize = 64;
        protected Item[] items;
        protected Button[] inventorySlotButtons = new Button[0];

        protected bool show = true;
        public bool autoPickup = false;

        private Button backPack;

        private int copperAmount = 999999;
        private int silverAmount = 999999;

        public Inventory(string n)
        {
            name = n;
            if (name == "playerInventory")
            {
                size.X = 680;
                size.Y = 104;
                pos.X = (Game1.frameSize.X - size.X) / 2 - 16;
                pos.Y = (Game1.frameSize.Y - size.Y) - 16;
                maxAmount = 9;
                currentAmount = 0;
                index = 0;
                items = new Item[maxAmount];

                Rectangle bpR = new Rectangle((int)(pos.X - 48 + size.X) + 16, (int)(pos.Y) - 16, 104, 104);
                backPack = new Button("backPackClicked", bpR);

                for (int i = 0; i < maxAmount; i++)
                {
                    items[i] = null;
                }

                // set buttons
                inventorySlotButtons = new Button[maxAmount];
                for (int i = 0; i < maxAmount; i++)
                {
                    Rectangle r = new Rectangle((int)pos.X + 4 + i * (gridSize + 4), (int)pos.Y + 4, gridSize, gridSize);
                    inventorySlotButtons[i] = new Button(("inventory" + i), r);
                }
            }
        }

        public bool addItem(Item it)
        {
            if (it == null) return false;
            if (it.isStackable())
            {
                int i;
                bool found = false;
                for (i = 0; i < maxAmount; i++)
                {
                    if (items[i] != null && items[i].getName() == it.getName() && (items[i].getCount() + it.getCount()) < items[i].getMaxCount())
                    {
                        found = true;
                        break;
                    }
                }
                if (!found)
                {
                    if (currentAmount >= maxAmount)
                    {
                        return false;
                    }
                    for (i = 0; i < maxAmount; i++)
                    {
                        if (items[i] == null)
                        {
                            currentAmount++;
                            items[i] = it;
                            break;
                        }
                    }
                }
                if (found)
                {
                    items[i].setCount(items[i].getCount() + it.getCount());
                }
            }
            if (!it.isStackable())
            {
                if (currentAmount >= maxAmount)
                {
                    return false;
                }
                for (int i = 0; i < maxAmount; i++)
                {
                    if (items[i] == null)
                    {
                        items[i] = it;
                        currentAmount++;
                        break;
                    }
                }
            }
            return true;
        }

        public bool addItem(Item it, int spot)
        {
            if (it == null || spot < 0 || spot >= maxAmount) return false;
            if (it.isStackable())
            {
                if (items[spot] == null)
                {
                    items[spot] = it;
                    currentAmount++;
                    return true;
                }
                if (items[spot] != null && items[spot].getName() == it.getName() && (items[spot].getCount() + it.getCount()) < items[spot].getMaxCount())
                {
                    items[spot].setCount(items[spot].getCount() + it.getCount());
                    return true;
                }
                return false;
            }
            if (!it.isStackable())
            {
                if (items[spot] == null)
                {
                    items[spot] = it;
                    currentAmount++;
                    return true;
                }
                return false;
            }
            return true;
        }

        public Item getItem(int i)
        {
            return items[i];
        }

        public Item removeItem(int i)
        {
            Item r = items[i];
            if (items[i] != null)
            {
                items[i] = null;
                currentAmount--;
            }
            return r;
        }

        public Item removeItem(string itemName)
        {
            Item r = null;

            int i;
            bool found = false;
            for (i = 0; i < maxAmount; i++)
            {
                if (items[i].getName() == itemName)
                {
                    found = true;
                    break;
                }
            }
            if (!found)
            {
                return r;
            }

            if (items[i] != null)
            {
                items[i] = null;
                currentAmount--;
            }
            return r;
        }

        public Item removeSingleItem(int i)
        {
            if (!items[i].isStackable())
            {
                Item ri = Item.Copy(items[i]);
                items[i] = null;
                ri.setCount(1);
                return ri;
            }
            if (items[i].getCount() == 1)
            {
                Item ri = Item.Copy(items[i]);
                items[i] = null;
                ri.setCount(1);
                return ri;
            }

            Item r = Item.Copy(items[i]);
            items[i].setCount(items[i].getCount() - 1);
            r.setCount(1);
            return r;
        }

        public Item removeSingleItem(string itemName)
        {
            Item r = null;

            int i;
            bool found = false;
            for (i = 0; i < maxAmount; i++)
            {
                if (items[i].getName() == itemName)
                {
                    found = true;
                    break;
                }
            }
            if (!found)
            {
                return r;
            }


            if (!items[i].isStackable())
            {
                Item ri = Item.Copy(items[i]);
                items[i] = null;
                ri.setCount(1);
                return ri;
            }
            if (items[i].getCount() == 1)
            {
                Item ri = Item.Copy(items[i]);
                items[i] = null;
                ri.setCount(1);
                return ri;
            }

            r = items[i];
            r.setCount(1);
            items[i].setCount(items[i].getCount() - 1);

            return r;
        }

        public void setItem(Item i, int index)
        {
            items[index] = i;
        }

        public int getIndex()
        {
            return index;
        }

        public override void update(GameTime gameTime)
        {
            currentAmount = 0;
            for (int i = 0; i < maxAmount; i++)
            {
                if(items[i] != null) currentAmount++;
            }
            backPack.update(gameTime, this);
            for (int i = 0; i < maxAmount; i++)
            {
                inventorySlotButtons[i].update(gameTime, this);
            }
            if (KeyboardHandler.isClickedR)
            {
                if(items[index] != null && items[index].GetType().Name == "Furniture")
                {
                    ((Furniture)items[index]).changeDir();
                }
            }

            /*
            // get inventory items
            if (name == "playerInventory")
            {
                Console.WriteLine(currentAmount //
                    + " " + ((items[0] != null) ? items[0].getName() : "null") //
                    + " " + ((items[1] != null) ? items[1].getName() : "null") //
                    + " " + ((items[2] != null) ? items[2].getName() : "null") //
                    + " " + ((items[3] != null) ? items[3].getName() : "null") //
                    + " " + ((items[4] != null) ? items[4].getName() : "null") //
                    + " " + ((items[5] != null) ? items[5].getName() : "null") //
                    + " " + ((items[6] != null) ? items[6].getName() : "null") //
                    + " " + ((items[7] != null) ? items[7].getName() : "null") //
                    + " " + ((items[8] != null) ? items[8].getName() : "null")); //
            }
            */
        }

        public override void draw(SpriteBatch spriteBatch)
        {
            if (show)
            {
                Camera.DrawUI(spriteBatch, SpriteSheet.GetSheet("inventory"), new Vector2(pos.X - 48, pos.Y - 16), new Rectangle(0, 0, (int)size.X, (int)size.Y), Color.White, 0f, 1.0f, false, 0.9999990f, 1.0f);
                
                Rectangle backPackR = new Rectangle(0, 0, 104, 104);
                if (!autoPickup) backPackR.X += backPackR.Width;
                Camera.DrawUI(spriteBatch, SpriteSheet.GetSheet("backPack"), new Vector2(pos.X - 48 + size.X + 16, pos.Y - 16), backPackR, Color.White, 0f, 1.0f, false, 0.9999990f, 1.0f);

                // monies
                Rectangle copperR = new Rectangle(208, 0, 32, 32);
                Rectangle silverR = new Rectangle(209, 32, 32, 32);
                Camera.DrawUI(spriteBatch, SpriteSheet.GetSheet("backPack"), new Vector2(pos.X - 48 + size.X + 16 + 104 + 12, pos.Y + 16), copperR, Color.White, 0f, 1.0f, false, 0.9999990f, 1.0f);
                Camera.DrawUI(spriteBatch, SpriteSheet.GetSheet("backPack"), new Vector2(pos.X - 48 + size.X + 16 + 104 + 12, pos.Y + 48), silverR, Color.White, 0f, 1.0f, false, 0.9999990f, 1.0f);
                Camera.DrawUIString(spriteBatch, SpriteSheet.GetFont("inventoryFont"), "" + copperAmount, new Vector2(pos.X - 48 + size.X + 16 + 120 + 32, pos.Y + 16 + 8), Color.White, 0f, 1.0f, false, 0.9999995f, 0.75f);
                Camera.DrawUIString(spriteBatch, SpriteSheet.GetFont("inventoryFont"), "" + silverAmount, new Vector2(pos.X - 48 + size.X + 16 + 120 + 32, pos.Y + 48 + 8), Color.White, 0f, 1.0f, false, 0.9999995f, 0.75f);

                Vector2 selectionSpritePos = pos;
                selectionSpritePos.Y -= 3;
                selectionSpritePos.X += (1 + index * (gridSize + 4));
                float alpha = 1.0f;
                if (items[index] == null) alpha = 0.25f;
                Camera.DrawUI(spriteBatch, SpriteSheet.GetSheet("selectionBox"), selectionSpritePos, new Rectangle(0, 0, gridSize + 8, gridSize + 8), Color.White, 0f, 1.0f, false, 0.9999993f, alpha);

                // draw furniture item mouse placment pos
                if (items[index] != null && (items[index].GetType().Name == "Furniture" || items[index].GetType().Name == "Chest") && !Player.usingWindow)
                {
                    Vector2 drawPos = (MouseHandler.Pos);
                    drawPos.X -= (MouseHandler.Pos.X + Camera.GetOffset().X) % Map.TILE_WIDTH;
                    drawPos.Y -= (MouseHandler.Pos.Y + Camera.GetOffset().Y) % Map.TILE_HEIGHT;
                    Camera.DrawUI(spriteBatch, SpriteSheet.GetSheet(items[index].getSheetName()), new Vector2(drawPos.X - items[index].getCollideFromSpriteOffset().X, drawPos.Y - items[index].getCollideFromSpriteOffset().Y), items[index].getSpriteSrcRect(), Color.White, 0f, 1.0f, false, 0.9999995f, 0.5f);
                }
                else if (items[index] != null)
                {
                    Vector2 drawPos = (MouseHandler.Pos);
                    drawPos.X -= items[index].getSpriteSrcRect().Width / 2;
                    drawPos.Y -= items[index].getSpriteSrcRect().Height / 2;
                    Camera.DrawUI(spriteBatch, SpriteSheet.GetSheet(items[index].getSheetName()), drawPos, items[index].getSpriteSrcRect(), Color.White, 0f, 1.0f, false, 0.9999995f, 0.5f);
                }

                int itemGrid = gridSize - 4;


                for (int i = 0; i < maxAmount; i++)
                {
                    if (items[i] != null)
                    {
                        // get scale
                        float length = 0;
                        float scale = 1.0f;
                        bool longerWidth = false;
                        if (items[i].getSpriteSrcRect().Width > items[i].getSpriteSrcRect().Height)
                        {
                            longerWidth = true;
                        }
                        if (longerWidth)
                        {
                            length = items[i].getSpriteSrcRect().Width;
                        }
                        else
                        {
                            length = items[i].getSpriteSrcRect().Height;
                        }
                        // scale = 1.0f / (gridSize / length);
                        scale = 1.0f / (length / itemGrid);

                        // get pos
                        Vector2 itemPos = pos;

                        itemPos.Y += 4;
                        itemPos.X += (4 + i * (itemGrid + 4 + 4));

                        if (items[i].isStackable())
                        {
                            Camera.DrawUIString(spriteBatch, SpriteSheet.GetFont("inventoryFont"), "" + items[i].getCount(), itemPos, Color.Black, 0f, 1.0f, false, 0.9999995f, 0.75f);
                        }

                        itemPos.X += (itemGrid + 2 - items[i].getSpriteSrcRect().Width * scale) / 2;
                        itemPos.Y += (itemGrid + 2 - items[i].getSpriteSrcRect().Height * scale) / 2;

                        Camera.DrawUI(spriteBatch, SpriteSheet.GetSheet(items[i].getSheetName()), itemPos, items[i].getSpriteSrcRect(), Color.White, 0f, scale, false, 0.9999994f, 1.0f);
                    }
                }
            }
        }

        public void setIndex(int i)
        {
            index = i;
        }

        public void addCopper(int i)
        {
            copperAmount += i;
        }

        public int getCopper()
        {
            return copperAmount;
        }

        public string getName()
        {
            return name;
        }

    }
}