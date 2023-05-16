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
    class InGameWindow
    {
        private static InGameWindow[] CurrentWindowStack;

        protected Vector2 pos;
        protected Vector2 size;

        public virtual void update(GameTime gameTime)
        {

        }

        public virtual void draw(SpriteBatch spriteBatch)
        {
            //Camera.Draw(spriteBatch, SpriteSheet.GetSheet(""), pos, new Rectangle(0, 0, (int)size.X, (int)size.Y), Color.White, false, 0.9999999f);
        }

        public Vector2 getPos()
        {
            return pos;
        }

        public Vector2 getSize()
        {
            return size;
        }

    }
}
