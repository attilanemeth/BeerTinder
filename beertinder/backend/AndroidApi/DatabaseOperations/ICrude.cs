using AndroidApi.Models;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace AndroidApi.DatabaseOperations
{
    public interface ICrude
    {
        void TestInsert();

        List<User> GetUsers();

        void CreateOrUpdateUserInfo(string uid,string latitude,string longitude);
        
    }
}
