using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using AndroidApi.DatabaseOperations;
using AndroidApi.Models;
using Microsoft.AspNetCore.Http;
using Microsoft.AspNetCore.Mvc;

namespace AndroidApi.Controllers
{
    
    [Route("api/[controller]")]
    [ApiController]
    public class PlacesController : ControllerBase
    {
        public static List<Bar> inProgress = new List<Bar>();

        private IPlaceQuery places;

        public PlacesController(IPlaceQuery _places)
        {
            this.places = _places;
        }
        [HttpGet("place")]
        public List<Bar> GetPlaces()
        {
            return places.GetBars();
        }

        [HttpGet("inProgress")]
        public List<Bar> Progres()
        {
            return inProgress;
        }
    }
}