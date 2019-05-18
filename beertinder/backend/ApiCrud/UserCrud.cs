using AndroidApi.DataContext;
using AndroidApi.Models;
using System;
using System.Collections.Generic;
using System.Text;

namespace ApiCrud
{
    class UserCrud
    {
        private readonly AppDataContext appDataContext;

        public UserCrud(AppDataContext app)
        {
            this.appDataContext = app;
        }

        public void InsertTest()
        {
            User u = new User() { UID = "fgffgfgfg", UserID = 1, Latitude = "40", Longitude = "10" };
            appDataContext.Users.Add(u);
            appDataContext.SaveChanges();
            

           

        }
        

    }
}
