using System;
using System.Collections.Generic;
using System.Diagnostics;
using System.Linq;
using System.Threading.Tasks;
using Microsoft.AspNetCore.Mvc;
using AndroidApi.Models;
using AndroidApi.DatabaseOperations;
using Microsoft.AspNetCore.Authorization;
using PusherServer;
using System.Net;

namespace AndroidApi.Controllers
{
    [Route("api")]
    public class HomeController : Controller
    {
        private ICrude crude;
        public HomeController(ICrude crude)
        {
            this.crude = crude;
        }

        [HttpGet("get/{id}")]
        public IActionResult Get(int id)
        {
            return Ok(id);
        }

      

        [HttpGet("users")]
        public List<User> GetAllUsers()
        {
            return crude.GetUsers();
        }
        [HttpGet("creatOrUpdate/{uid}/{longitude}/{latitude}")]
        public IActionResult CreateOrUpdateUserInfo(string uid, string longitude, string latitude)
        {
            this.crude.CreateOrUpdateUserInfo(uid, latitude, longitude);
            return Ok("OK");
        }
    }

       

    
}
