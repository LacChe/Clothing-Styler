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
    class StoreInventory : Inventory
    {

        private int pageW = 4, pageH = 7, currentPage = 0;
        private int pageOffsetX = 100, pageOffsetY = 32;

        private bool open = false;

        private int stockAmount, itemIndex = 0;

        private Button exit, lastPage, nextPage, buy;

        public StoreInventory(string n) : base(n)
        {
            size.X = 776;
            size.Y = 544;
            pos.X = (Game1.frameSize.X - 616) / 2 + 616 / 2 - size.X / 2;
            pos.Y = (Game1.frameSize.Y - 72) - 16 - size.Y - 32 - 32;
            name = n;
            maxAmount = -1;

            if (name == "cropsMarket")
            {
                stockAmount = 60;
                currentAmount = 60;
                items = new Crop[stockAmount];
                items[0] = new Crop(new Vector2(0, 0), "peas", 8);
                items[1] = new Crop(new Vector2(0, 0), "driedSoybeans", 8);
                items[2] = new Crop(new Vector2(0, 0), "soybeans", 8);
                items[3] = new Crop(new Vector2(0, 0), "sunflowerLeaves", 8);
                items[4] = new Crop(new Vector2(0, 0), "shepherdsPurse", 8);
                items[5] = new Crop(new Vector2(0, 0), "celtuce", 8);
                items[6] = new Crop(new Vector2(0, 0), "rice", 8);
                items[7] = new Crop(new Vector2(0, 0), "chestnuts", 8);
                items[8] = new Crop(new Vector2(0, 0), "wheat", 8);
                items[9] = new Crop(new Vector2(0, 0), "hawthorn", 8);
                items[10] = new Crop(new Vector2(0, 0), "celery", 8);
                items[11] = new Crop(new Vector2(0, 0), "cabbage", 8);
                items[12] = new Crop(new Vector2(0, 0), "dates", 8);
                items[13] = new Crop(new Vector2(0, 0), "plum", 8);
                items[14] = new Crop(new Vector2(0, 0), "apricots", 8);
                items[15] = new Crop(new Vector2(0, 0), "scallions", 8);
                items[16] = new Crop(new Vector2(0, 0), "ginger", 8);
                items[17] = new Crop(new Vector2(0, 0), "grapes", 8);
                items[18] = new Crop(new Vector2(0, 0), "pomegranate", 8);
                items[19] = new Crop(new Vector2(0, 0), "lychee", 8);
                items[20] = new Crop(new Vector2(0, 0), "strawberry", 8);
                items[21] = new Crop(new Vector2(0, 0), "garlic", 8);
                items[22] = new Crop(new Vector2(0, 0), "honey", 8);
                items[23] = new Crop(new Vector2(0, 0), "sesame", 8);
                items[24] = new Crop(new Vector2(0, 0), "radish", 8);
                items[25] = new Crop(new Vector2(0, 0), "spinach", 8);
                items[26] = new Crop(new Vector2(0, 0), "cucumber", 8);
                items[27] = new Crop(new Vector2(0, 0), "eggplant", 8);
                items[28] = new Crop(new Vector2(0, 0), "winterMelon", 8);
                items[29] = new Crop(new Vector2(0, 0), "apple", 8);
                items[30] = new Crop(new Vector2(0, 0), "banana", 8);
                items[31] = new Crop(new Vector2(0, 0), "orange", 8);
                items[32] = new Crop(new Vector2(0, 0), "pepper", 8);
                items[33] = new Crop(new Vector2(0, 0), "tomato", 8);
                items[34] = new Crop(new Vector2(0, 0), "watermelon", 8);
                items[35] = new Crop(new Vector2(0, 0), "sweetPotato", 8);
                items[36] = new Crop(new Vector2(0, 0), "pear", 8);
                items[37] = new Crop(new Vector2(0, 0), "onion", 8);
                items[38] = new Crop(new Vector2(0, 0), "durian", 8);
                items[39] = new Crop(new Vector2(0, 0), "gourd", 8);
                items[40] = new Crop(new Vector2(0, 0), "lotusRoot", 8);
                items[41] = new Crop(new Vector2(0, 0), "lotus", 8);
                items[42] = new Crop(new Vector2(0, 0), "grapefruit", 8);
                items[43] = new Crop(new Vector2(0, 0), "peach", 8);
                items[44] = new Crop(new Vector2(0, 0), "dragonFruit", 8);
                items[45] = new Crop(new Vector2(0, 0), "cantaloupe", 8);
                items[46] = new Crop(new Vector2(0, 0), "pumpkin", 8);
                items[47] = new Crop(new Vector2(0, 0), "potato", 8);
                items[48] = new Crop(new Vector2(0, 0), "winterSquash", 8);
                items[49] = new Crop(new Vector2(0, 0), "corn", 8);
                items[50] = new Crop(new Vector2(0, 0), "whiteMushrooms", 8);
                items[51] = new Crop(new Vector2(0, 0), "goldenMushrooms", 8);
                items[52] = new Crop(new Vector2(0, 0), "shiitakeMushrooms", 8);
                items[53] = new Crop(new Vector2(0, 0), "chives", 8);
                items[54] = new Crop(new Vector2(0, 0), "wormwood", 8);
                items[55] = new Crop(new Vector2(0, 0), "bitterMelon", 8);
                items[56] = new Crop(new Vector2(0, 0), "bambooShoots", 8);
                items[57] = new Crop(new Vector2(0, 0), "waterSpinach", 8);
                items[58] = new Crop(new Vector2(0, 0), "sugarcane", 8);
                items[59] = new Crop(new Vector2(0, 0), "konjac", 8);
            }

            if (name == "herbsMarket")
            {
                stockAmount = 30;
                currentAmount = 30;
                items = new Herb[stockAmount];
                items[0] = new Herb(new Vector2(0, 0), "sageRoot", 3);
                items[1] = new Herb(new Vector2(0, 0), "frankincense", 3);
                items[2] = new Herb(new Vector2(0, 0), "ginseng", 3);
                items[3] = new Herb(new Vector2(0, 0), "hairyveinAgrimonia", 3);
                items[4] = new Herb(new Vector2(0, 0), "fleeceflower", 3);
                items[5] = new Herb(new Vector2(0, 0), "corydalis", 3);
                items[6] = new Herb(new Vector2(0, 0), "piloseAsiabell", 3);
                items[7] = new Herb(new Vector2(0, 0), "chineseCaterpillarFungus", 3);
                items[8] = new Herb(new Vector2(0, 0), "cassiaSeed", 3);
                items[9] = new Herb(new Vector2(0, 0), "manyprickleAcanthopanax", 3);
                items[10] = new Herb(new Vector2(0, 0), "ragwort", 3);
                items[11] = new Herb(new Vector2(0, 0), "pinellia", 3);
                items[12] = new Herb(new Vector2(0, 0), "tamariskoidSpikemoss", 3);
                items[13] = new Herb(new Vector2(0, 0), "magnoliaBark", 3);
                items[14] = new Herb(new Vector2(0, 0), "rehmanniaRoot", 3);
                items[15] = new Herb(new Vector2(0, 0), "earthworm", 3);
                items[16] = new Herb(new Vector2(0, 0), "stringyStonecrop", 3);
                items[17] = new Herb(new Vector2(0, 0), "selfheal", 3);
                items[18] = new Herb(new Vector2(0, 0), "sorrelRhubarb", 3);
                items[19] = new Herb(new Vector2(0, 0), "tallGastrodia", 3);
                items[20] = new Herb(new Vector2(0, 0), "glossyFrivetFruit", 3);
                items[21] = new Herb(new Vector2(0, 0), "fieldThistle", 3);
                items[22] = new Herb(new Vector2(0, 0), "szechuanLovageRoot", 3);
                items[23] = new Herb(new Vector2(0, 0), "chineseAngelicaRoot", 3);
                items[24] = new Herb(new Vector2(0, 0), "eclipta", 3);
                items[25] = new Herb(new Vector2(0, 0), "chineseRose", 3);
                items[26] = new Herb(new Vector2(0, 0), "aucklandiaRoot", 3);
                items[27] = new Herb(new Vector2(0, 0), "cinnabar", 3);
                items[28] = new Herb(new Vector2(0, 0), "eucommiaBark", 3);
                items[29] = new Herb(new Vector2(0, 0), "dyersWoadRoot", 3);
            }

            // set buttons
            Rectangle rE = new Rectangle((int)pos.X + 38, (int)pos.Y + 80, 32, 32);
            exit = new Button(("exitStoreInventory"), rE);
            rE = new Rectangle((int)pos.X + 38, (int)pos.Y + 135, 32, 32);
            lastPage = new Button(("lastPageStoreInventory"), rE);
            rE = new Rectangle((int)pos.X + 38, (int)pos.Y + 190, 32, 32);
            nextPage = new Button(("nextPageStoreInventory"), rE);
            rE = new Rectangle((int)pos.X + 671, (int)pos.Y + 438, 48, 48);
            buy = new Button(("buyStoreInventory"), rE);

            inventorySlotButtons = new Button[stockAmount];

            for (int i = 0; i < pageW * pageH; i++)
            {
                Rectangle r = new Rectangle((int)pos.X + 4 + (i % 4) * (gridSize + 4), (int)pos.Y + 4 + (i / 4) * (gridSize + 4), gridSize, gridSize);

                r.X += pageOffsetX;
                r.Y += pageOffsetY;
                inventorySlotButtons[i] = new Button(("storeInventory" + i), r);
            }
        }

        public override void update(GameTime gameTime)
        {
            exit.update(gameTime, this);
            lastPage.update(gameTime, this);
            nextPage.update(gameTime, this);
            buy.update(gameTime, this);

            for (int i = 0; i < pageW * pageH; i++)
            {
                string tempOut = inventorySlotButtons[i].update(gameTime, this);
                if (tempOut != "" && tempOut != "endFunc")
                {
                    if ((i + currentPage * pageH * pageW) < stockAmount && items[i + currentPage * pageH * pageW] != null) {
                        itemIndex = i + currentPage * pageH * pageW;
                    }
                }
            }
        }

        public override void draw(SpriteBatch spriteBatch)
        {
            if (show)
            {
                Camera.DrawUI(spriteBatch, SpriteSheet.GetSheet("storeInventory"), pos, new Rectangle(0, 0, (int)size.X, (int)size.Y), Color.White, 0f, 1.0f, false, 0.9999990f, 1.0f);
                if(currentPage == 0) Camera.DrawUI(spriteBatch, SpriteSheet.GetSheet("chestInventory"), new Vector2(pos.X + 39, pos.Y + 138), new Rectangle(39, 138, 32, 32), Color.White, 0f, 1.0f, false, 0.9999991f, 0.5f);
                if (currentPage == (int)((stockAmount / (pageW * pageH)))) Camera.DrawUI(spriteBatch, SpriteSheet.GetSheet("chestInventory"), new Vector2(pos.X + 39, pos.Y + 190), new Rectangle(39, 190, 32, 32), Color.White, 0f, 1.0f, false, 0.9999991f, 0.5f);


                // left side
                for (int i = currentPage * pageH * pageW; i < currentPage * pageH * pageW + pageH * pageW; i++)
                {
                    if (i < stockAmount && items[i] != null)
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
                        scale = 1.0f / (length / gridSize);

                        // get pos
                        Vector2 itemPos = pos;

                        itemPos.X = inventorySlotButtons[i - currentPage * pageH * pageW].getRect().X;
                        itemPos.Y = inventorySlotButtons[i - currentPage * pageH * pageW].getRect().Y;
                        
                        itemPos.Y += (gridSize - items[i].getSpriteSrcRect().Height * scale) / 2;
                        itemPos.X += (gridSize - items[i].getSpriteSrcRect().Width * scale) / 2;

                        Camera.DrawUI(spriteBatch, SpriteSheet.GetSheet(items[i].getSheetName()), itemPos, items[i].getSpriteSrcRect(), Color.White, 0f, scale, false, 0.9999994f, 1.0f);
                    }
                }
                // right side
                if (items[itemIndex] != null)
                {
                    // get scale
                    float length = 0;
                    float scale = 1.0f;
                    bool longerWidth = false;
                    if (items[itemIndex].getSpriteSrcRect().Width > items[itemIndex].getSpriteSrcRect().Height)
                    {
                        longerWidth = true;
                    }
                    if (longerWidth)
                    {
                        length = items[itemIndex].getSpriteSrcRect().Width;
                    }
                    else
                    {
                        length = items[itemIndex].getSpriteSrcRect().Height;
                    }
                    // scale = 1.0f / (gridSize / length);
                    scale = 1.0f / (length / 96);
                    // get pos
                    Vector2 itemPos = pos;
                    itemPos.X += 421;
                    itemPos.Y += 53;

                    Camera.DrawUI(spriteBatch, SpriteSheet.GetSheet(items[itemIndex].getSheetName()), itemPos, items[itemIndex].getSpriteSrcRect(), Color.White, 0f, scale, false, 0.9999994f, 1.0f);

                    itemPos = pos;
                    itemPos.X += 539 + 16;
                    itemPos.Y += 33 + 16;

                    Camera.DrawUIString(spriteBatch, SpriteSheet.GetFont("inventoryFont"), items[itemIndex].getName().ToUpper(), itemPos, Color.Black, 0f, 1.0f, false, 0.9999995f, 0.75f);
                    itemPos.Y += 32;
                    Camera.DrawUIString(spriteBatch, SpriteSheet.GetFont("inventoryFont"), "" + items[itemIndex].getValue(), itemPos, Color.Black, 0f, 1.0f, false, 0.9999995f, 0.75f);

                    itemPos = pos;
                    itemPos.X += 401 + 16;
                    itemPos.Y += 171 + 16;
                    Camera.DrawUIString(spriteBatch, SpriteSheet.GetFont("inventoryFont"), items[itemIndex].getDescription().ToUpper(), itemPos, Color.Black, 0f, 1.0f, false, 0.9999995f, 0.75f);
                }
            }
        }

        public Item getCurrentItem()
        {
            return items[itemIndex];
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

        public void changePage(int i)
        {
            if(i == 1)
            {
                currentPage += 1;
                int maxPageAmount = 1;
                maxPageAmount = stockAmount / (pageW * pageH);
                if (stockAmount % (pageW * pageH) != 0) maxPageAmount += 1;
                if (currentPage >= maxPageAmount) currentPage = maxPageAmount - 1;
            }
            if (i == -1)
            {
                currentPage -= 1;
                if (currentPage < 0) currentPage = 0;
            }
        }

    }
}