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
    class MouseHandler
    {

        public static Vector2 Pos;
        public static bool releasedL = true, isClickedL = false, isHeldL = false;
        public static bool releasedR = true, isClickedR = false, isHeldR = false;
        private static int holdCountL = 0, holdCountR = 0, holdLimit = 60;

        public static void Update()
        {
            Pos.X = Mouse.GetState().X;
            Pos.Y = Mouse.GetState().Y;

            isClickedL = false;
            if (Mouse.GetState().LeftButton == ButtonState.Pressed && releasedL == false)
            {
                holdCountL++;
                if(holdCountL >= holdLimit)
                {
                    isHeldL = true;
                }
            }
            if (Mouse.GetState().LeftButton == ButtonState.Pressed && releasedL == true)
            {
                releasedL = false;
                isClickedL = true;
            }
            if (Mouse.GetState().LeftButton == ButtonState.Released)
            {
                releasedL = true;
                holdCountL = 0;
                isHeldL = false;
            }


            isClickedR = false;
            if (Mouse.GetState().RightButton == ButtonState.Pressed && releasedR == false)
            {
                holdCountR++;
                if (holdCountR >= holdLimit)
                {
                    isHeldR = true;
                }
            }
            if (Mouse.GetState().RightButton == ButtonState.Pressed && releasedR == true)
            {
                releasedR = false;
                isClickedR = true;
            }
            if (Mouse.GetState().RightButton == ButtonState.Released)
            {
                releasedR = true;
                holdCountR = 0;
                isHeldR = false;
            }
        }
    }
}