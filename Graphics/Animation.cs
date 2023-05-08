using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using Microsoft.Xna.Framework;
using Microsoft.Xna.Framework.Content;
using Microsoft.Xna.Framework.Graphics;

namespace vet
{
    class Animation
    {
        private string sheetName;
        private int speedIndex, speed, frames, index;
        private Rectangle[] rects;
        private bool horizFlip = false;
        private bool active = true;
        private bool looping = true;

        public Animation(string name, int f, Rectangle[] r, int s = 5)
        {
            sheetName = name;
            speed = s;
            speedIndex = 0;
            frames = f;
            index = 0;
            rects = r;
        }
        public void update(GameTime gameTime)
        {
            
            if (active)
            {
                speedIndex++;
                if (speedIndex >= speed)
                {
                    speedIndex = 0;
                    index++;
                    if (index >= frames) index = 0;
                }
                if (index == frames && looping == false) active = false;
            }
        }

        public void draw(SpriteBatch spriteBatch, Vector2 pos, float rotate, float depth)
        {
            if (active)
            {
                Camera.Draw(spriteBatch, SpriteSheet.GetSheet(sheetName), pos, rects[index], Color.White, rotate, 1.0f, horizFlip, depth, 1.0f);
            }
        }
        public void setHorizontalFlip()
        {
            horizFlip = true;
        }

        public void setActive(bool b)
        {
            active = b;
        }

        public bool IsActive()
        {
            return active;
        }

        public int getIndex()
        {
            return index;
        }

    }
}