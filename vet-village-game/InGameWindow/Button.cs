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
    class Button
    {

        private Rectangle rect;
        private Rectangle srcRect;
        private string name;
        private string backgroundSprite = null;
        private string backgroundSpriteDown = null;

        private bool isHeldL = false, isHeldR = false;
        private int holdCountL = 0, holdCountR = 0, holdLimit = 60;

        public bool UIbutton = true;

        public Button(string n, Rectangle r, String sName = "null")
        {
            backgroundSprite = sName;
            if (backgroundSprite != "null")
            {
                backgroundSpriteDown = sName + "Down";
            }


            name = n;
            srcRect = r;
            rect = r;
        }

        public void draw(SpriteBatch spriteBatch, Vector2 pos, float depth)
        {
            
            if (backgroundSprite != null && !(MouseHandler.isClickedL && rect.Contains(MouseHandler.Pos)))
            {
                Camera.Draw(spriteBatch, SpriteSheet.GetSheet(backgroundSprite), pos, rect, Color.White, 0f, 1.0f, false, depth, 1.0f);
            } 
            else if (backgroundSpriteDown != null && (MouseHandler.isClickedL && rect.Contains(MouseHandler.Pos)))
            {
                Camera.Draw(spriteBatch, SpriteSheet.GetSheet(backgroundSpriteDown), pos, rect, Color.White, 0f, 1.0f, false, depth, 1.0f);
            }
        }

        public string update(GameTime gameTime, Object o)
        {
            if (!UIbutton)
            {
                rect = srcRect;
                rect.X += ((int)((Item)o).getMapPos().X - (int)Camera.GetOffset().X);
                rect.Y += ((int)((Item)o).getMapPos().Y - (int)Camera.GetOffset().Y);
            }
            if (holdCountL >= holdLimit && !isHeldL)
            {
                isHeldL = true;
            }
            if ((MouseHandler.releasedL || rect.Contains(MouseHandler.Pos)) && isHeldL)
            {
                holdCountL = 0;
                isHeldL = false;
            }
            if (holdCountR >= holdLimit && !isHeldR)
            {
                isHeldR = true;
            }
            if ((MouseHandler.releasedR || rect.Contains(MouseHandler.Pos)) && isHeldR)
            {
                holdCountR = 0;
                isHeldR = false;
            }

            if ((MouseHandler.isClickedL || MouseHandler.isClickedR) && rect.Contains(MouseHandler.Pos))
            {
                holdCountL++;
                switch (name)
                {
                    case "itemClickArea":
                        return "itemClicked";
                    case "furnitureClickArea":
                        return "furnitureClicked";
                    case "backPackClicked":
                        if (MouseHandler.isClickedR) ((Inventory)o).autoPickup = !((Inventory)o).autoPickup;
                        return "backPackClicked";
                    case "chestClicked":
                        if (MouseHandler.isClickedL) ((Chest)o).inventory.activate();
                        break;
                    case "exitChestInventory":
                        if (MouseHandler.isClickedL) ((ChestInventory)o).activate();
                        break;
                    case "exitStoreInventory":
                        if (MouseHandler.isClickedL) ((StoreInventory)o).activate();
                        break;
                    case "lastPageStoreInventory":
                        if (MouseHandler.isClickedL) ((StoreInventory)o).changePage(-1);
                        break;
                    case "nextPageStoreInventory":
                        if (MouseHandler.isClickedL) ((StoreInventory)o).changePage(1);
                        break;
                    case "buyStoreInventory":
                        int buyCount = 0;
                        if (MouseHandler.isClickedL && !MouseHandler.isClickedR) buyCount = 1;
                        else if (MouseHandler.isClickedR && !MouseHandler.isClickedL) buyCount = 8;
                        for (int i = 0; i < buyCount; i++)
                        {
                            if (Player.player.inventory.getCopper() >= ((StoreInventory)o).getCurrentItem().getValue() && Player.player.inventory.addItem(Item.Copy(((StoreInventory)o).getCurrentItem())))
                                Player.player.inventory.addCopper(-1 * ((StoreInventory)o).getCurrentItem().getValue());
                        }
                        break;
                    case "inventory0":
                        if (MouseHandler.isClickedR)
                        {
                            Item temp = Player.player.inventory.getItem(0);
                            Player.player.inventory.setItem(Player.player.inventory.getItem(Player.player.inventory.getIndex()), 0);
                            Player.player.inventory.setItem(temp, Player.player.inventory.getIndex());
                        }
                        Player.player.inventory.setIndex(0);
                        break;
                    case "inventory1":
                        if (MouseHandler.isClickedR)
                        {
                            Item temp = Player.player.inventory.getItem(1);
                            Player.player.inventory.setItem(Player.player.inventory.getItem(Player.player.inventory.getIndex()), 1);
                            Player.player.inventory.setItem(temp, Player.player.inventory.getIndex());
                        }
                        Player.player.inventory.setIndex(1);
                        break;
                    case "inventory2":
                        if (MouseHandler.isClickedR)
                        {
                            Item temp = Player.player.inventory.getItem(2);
                            Player.player.inventory.setItem(Player.player.inventory.getItem(Player.player.inventory.getIndex()), 2);
                            Player.player.inventory.setItem(temp, Player.player.inventory.getIndex());
                        }
                        Player.player.inventory.setIndex(2);
                        break;
                    case "inventory3":
                        if (MouseHandler.isClickedR)
                        {
                            Item temp = Player.player.inventory.getItem(3);
                            Player.player.inventory.setItem(Player.player.inventory.getItem(Player.player.inventory.getIndex()), 3);
                            Player.player.inventory.setItem(temp, Player.player.inventory.getIndex());
                        }
                        Player.player.inventory.setIndex(3);
                        break;
                    case "inventory4":
                        if (MouseHandler.isClickedR)
                        {
                            Item temp = Player.player.inventory.getItem(4);
                            Player.player.inventory.setItem(Player.player.inventory.getItem(Player.player.inventory.getIndex()), 4);
                            Player.player.inventory.setItem(temp, Player.player.inventory.getIndex());
                        }
                        Player.player.inventory.setIndex(4);
                        break;
                    case "inventory5":
                        if (MouseHandler.isClickedR)
                        {
                            Item temp = Player.player.inventory.getItem(5);
                            Player.player.inventory.setItem(Player.player.inventory.getItem(Player.player.inventory.getIndex()), 5);
                            Player.player.inventory.setItem(temp, Player.player.inventory.getIndex());
                        }
                        Player.player.inventory.setIndex(5);
                        break;
                    case "inventory6":
                        if (MouseHandler.isClickedR)
                        {
                            Item temp = Player.player.inventory.getItem(6);
                            Player.player.inventory.setItem(Player.player.inventory.getItem(Player.player.inventory.getIndex()), 6);
                            Player.player.inventory.setItem(temp, Player.player.inventory.getIndex());
                        }
                        Player.player.inventory.setIndex(6);
                        break;
                    case "inventory7":
                        if (MouseHandler.isClickedR)
                        {
                            Item temp = Player.player.inventory.getItem(7);
                            Player.player.inventory.setItem(Player.player.inventory.getItem(Player.player.inventory.getIndex()), 7);
                            Player.player.inventory.setItem(temp, Player.player.inventory.getIndex());
                        }
                        Player.player.inventory.setIndex(7);
                        break;
                    case "inventory8":
                        if (MouseHandler.isClickedR)
                        {
                            Item temp = Player.player.inventory.getItem(8);
                            Player.player.inventory.setItem(Player.player.inventory.getItem(Player.player.inventory.getIndex()), 8);
                            Player.player.inventory.setItem(temp, Player.player.inventory.getIndex());
                        }
                        Player.player.inventory.setIndex(8);
                        break;
                    default:
                        if (MouseHandler.isClickedL)
                        {
                            return name + "l";
                        }
                        if (MouseHandler.isClickedR)
                        {
                            return name + "r";
                        }
                        return name;
                }
            }
            return "endFunc";
        }

        public Rectangle getRect()
        {
            return rect;
        }

        public void setRect(Rectangle r)
        {
            rect = r;
        }

    }
}