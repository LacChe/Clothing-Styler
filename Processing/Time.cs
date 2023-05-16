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
    class Time
    {
        private static long elapsedTime = 0;
        private static long maxTime = 60 * 60 * 60 * 24 * 7 * 54; // year
        private static int dayLength = 60 * 60 * 20;
        // private static int dayLength = 60 * 40;

        public static void Update(GameTime gameTime)
        {
            elapsedTime++;
            // elapsedTime = 60 * 20;

            // less night
            /*
            float dayPercent = 0.0f;
            if (Time.getDayPercent() >= 0.5)
            {
                dayPercent = (2.0f - Time.getDayPercent() * 2);
            }
            else if (Time.getDayPercent() < 0.5)
            {
                dayPercent = (Time.getDayPercent() * 2);
            }
            dayPercent = dayPercent * 5 - 2;
            if (dayPercent > 1) dayPercent = 1;
            if (dayPercent < 0) dayPercent = 0;
            elapsedTime += (int)(10 * (1.0 - dayPercent));
            */
        }

        public static void Draw(SpriteBatch spriteBatch)
        {
            // calculate circle pts
            Vector2 bodyPos = new Vector2(Player.player.inventory.getPos().X - 48 - 8, Player.player.inventory.getPos().Y - 16 + 8 + 44 - 8);
            float angle = getDayPercent() * (float)Math.PI * 2f;
            Vector2 circleOffset = new Vector2((float)(27 * Math.Cos(angle)), (float)(27 * Math.Sin(angle)));

            Camera.DrawUI(spriteBatch, SpriteSheet.GetSheet("time"), bodyPos - circleOffset, new Rectangle(132, 14, 14, 14), Color.White, 0f, 1.0f, false, 0.9999989f, 1.0f);
            Camera.DrawUI(spriteBatch, SpriteSheet.GetSheet("time"), bodyPos + circleOffset, new Rectangle(132, 0, 14, 14), Color.White, 0f, 1.0f, false, 0.9999989f, 1.0f);
            Camera.DrawUI(spriteBatch, SpriteSheet.GetSheet("time"), new Vector2(Player.player.inventory.getPos().X - 48 - 44, Player.player.inventory.getPos().Y - 16 + 8), new Rectangle(0, 0, 44, 88), Color.White, 0f, 1.0f, false, 0.9999988f, 1.0f);
            Camera.DrawUI(spriteBatch, SpriteSheet.GetSheet("time"), new Vector2(Player.player.inventory.getPos().X - 48 - 44, Player.player.inventory.getPos().Y - 16 + 8), new Rectangle(44, 0, 88, 88), Color.White, getDayPercent() * (float)Math.PI * 2f + (float)Math.PI * 0.5f, 1.0f, false, 0.9999987f, 1.0f);
        }

        public static int getDayLength()
        {
            return dayLength;
        }

        public static long getElapsedTime()
        {
            return elapsedTime;
        }

        public static float getDayPercent()
        {
            return (elapsedTime % dayLength) / (float)dayLength + 0.2f;
        }

    }
}