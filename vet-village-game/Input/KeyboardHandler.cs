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
    class KeyboardHandler
    {
        public static bool releasedR = true, isClickedR = false, isHeldR = false;
        private static int holdCountR = 0, holdLimit = 60;

        public static void Update()
        {

            isClickedR = false;
            if (Keyboard.GetState().IsKeyDown(Keys.R) && releasedR == false)
            {
                holdCountR++;
                if (holdCountR >= holdLimit)
                {
                    isHeldR = true;
                }
            }
            if (Keyboard.GetState().IsKeyDown(Keys.R) && releasedR == true)
            {
                releasedR = false;
                isClickedR = true;
            }
            if (!Keyboard.GetState().IsKeyDown(Keys.R))
            {
                releasedR = true;
                holdCountR = 0;
                isHeldR = false;
            }
        }
    }
}
