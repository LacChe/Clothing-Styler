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
    class Item
    {

        protected string className = "item";
        public bool playerInteractable = true;

        protected bool stackable = false;
        protected int stackCount, maxCount;
        protected string name, sheetName;
        protected Rectangle spriteSrcRect;
        protected Rectangle collideRect;
        protected Vector2 collideFromSpriteOffset = new Vector2(0, 0);
        protected Vector2 spriteSheetOffset = new Vector2(0, 0);

        // furniture
        protected Rectangle spriteSrcRectAlt;
        protected Rectangle collideRectAlt;
        protected Vector2 collideFromSpriteOffsetAlt = new Vector2(0, 0);
        protected int placeDir = 0;

        protected int atMap;

        protected Vector2 mapPos;

        protected Button button;

        protected bool smallItem = true;

        protected int value = 20;
        protected string description = "description description d\n" +
                                       "escription description de\n" +
                                       "scription description des\n" +
                                       "cription description desc\n" +
                                       "ription description descr\n" +
                                       "iption";

        public Item()
        {

        }

        public virtual void update(GameTime gameTime)
        {
            button.update(gameTime, this);
        }

        public virtual void draw(SpriteBatch spriteBatch)
        {
            Rectangle spriteSrc = spriteSrcRect;
            spriteSrc.X += (int)spriteSheetOffset.X;
            spriteSrc.Y += (int)spriteSheetOffset.Y;
            float depth = (mapPos.Y + spriteSrcRect.Height / 2) / (Map.Height(atMap) * Map.TILE_HEIGHT);
            Camera.Draw(spriteBatch, SpriteSheet.GetSheet(sheetName), new Vector2(mapPos.X - collideFromSpriteOffset.X, mapPos.Y - collideFromSpriteOffset.Y), spriteSrc, Color.White, 0f, 1.0f, false, depth, 1.0f);
        }

        public virtual bool activateOnPlayer()
        {
            if (this.GetType().Name == "UpperClothing") // wear upper clothing
            {
                UpperClothing tempC = Player.player.getUpperClothing();
                Player.player.setUpperClothing((UpperClothing)this);

                Player.player.inventory.removeItem(Player.player.inventory.getIndex());

                Player.player.inventory.addItem(tempC);
            }
            return true;
        }

        public virtual bool isStackable()
        {
            return stackable;
        }

        public virtual int getCount()
        {
            return stackCount;
        }

        public virtual int getMaxCount()
        {
            return maxCount;
        }

        public virtual void setCount(int i)
        {
            stackCount = i;
        }

        public virtual string getName()
        {
            return name;
        }

        public virtual string getSheetName()
        {
            return sheetName;
        }

        public virtual Rectangle getSpriteSrcRect()
        {
            Rectangle spriteSrc = spriteSrcRect;
            spriteSrc.X += (int)spriteSheetOffset.X;
            spriteSrc.Y += (int)spriteSheetOffset.Y;
            return spriteSrc;
        }

        public virtual Vector2 getCollideFromSpriteOffset()
        {
            return collideFromSpriteOffset;
        }

        public virtual Vector2 getMapPos()
        {
            return mapPos;
        }

        public virtual void setAtMap(int m)
        {
            atMap = m;
        }

        public virtual void setMapPos(Vector2 pos)
        {
            mapPos = pos;
        }

        public virtual Rectangle getCollideRect()
        {
            Rectangle col = collideRect;
            col.X += (int)mapPos.X;
            col.Y += (int)mapPos.Y;
            return col;
        }

        public bool isSmallItem()
        {
            return smallItem;
        }

        public static T Copy<T>(T item) where T : Item
        {
            T temp = (T)(new Item());

            temp.className = item.className;
            temp.stackable = item.stackable;
            temp.stackCount = item.stackCount;
            temp.maxCount = item.maxCount;
            temp.name = item.name;
            temp.sheetName = item.sheetName;
            temp.spriteSrcRect = item.spriteSrcRect;
            temp.collideRect = item.collideRect;
            temp.collideFromSpriteOffset = item.collideFromSpriteOffset;
            temp.spriteSheetOffset = item.spriteSheetOffset;

            // furniture
            temp.spriteSrcRectAlt = item.spriteSrcRectAlt;
            temp.collideRectAlt = item.collideRectAlt;
            temp.collideFromSpriteOffsetAlt = item.collideFromSpriteOffsetAlt;
            temp.placeDir = item.placeDir;

            temp.atMap = item.atMap;

            temp.mapPos = item.mapPos;

            temp.button = item.button;

            temp.smallItem = item.smallItem;

            return temp;
        }

        public int getValue()
        {
            return value;
        }

        public string getDescription()
        {
            return description;
        }

        public string getClass()
        {
            return className;
        }

    }
}