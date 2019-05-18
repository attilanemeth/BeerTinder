using AndroidApi.DataContext;
using AndroidApi.Models;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace AndroidApi.DatabaseOperations
{
    public class UserCrudOperation : ICrude
    {
        private AppDataContext dataContext;


        public UserCrudOperation(AppDataContext app)
        {
            this.dataContext = app;
        }

        public void CreateOrUpdateUserInfo(string uid, string latitude, string longitude)
        {
            if (CheckUIDisExist(uid))
            {
                var u=dataContext.Users.Where(x => x.UID == uid).First();
                u.Latitude = "43434";
                u.Longitude = "54535";
                dataContext.Update(u);
                dataContext.SaveChanges();
            }
            else
            {
            User u = new User() { UID = uid, Latitude = latitude, Longitude = longitude };
                dataContext.Users.Add(u);
                dataContext.SaveChanges();

            }
        }

        public List<User> GetUsers()
        {
            return dataContext.Users.ToList();
        }

        public void TestInsert()
        {
            List<Bar> b = new List<Bar>();
            b.Add(new Bar() { Latitude= "47.53543",Longitude= "19.039442",Name= "Legenda Sörfőzde Pub" });
            b.Add(new Bar() { Latitude = "47.520278", Longitude = "19.046886", Name = "Stég Pub" });
            b.Add(new Bar() { Latitude = "47.510346", Longitude = "19.030973", Name = "The Pointer Pub" });

            dataContext.AddRange(b);
            dataContext.SaveChanges();




        }

        private bool CheckUIDisExist(string uid_)
        {
            int count = dataContext.Users.Where(x => x.UID == uid_).Count();
            if (count > 0)
            {
                return true;
            }
            else
            {
                return false;
            }
        }


    }
}
