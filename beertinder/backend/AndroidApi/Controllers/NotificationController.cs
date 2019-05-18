using System;
using System.Collections.Generic;
using System.Linq;
using System.Net;
using System.Net.Http;
using System.Threading.Tasks;
using AndroidApi.DatabaseOperations;
using AndroidApi.JsonObjects;
using AndroidApi.Notification;
using Microsoft.AspNetCore.Http;
using Microsoft.AspNetCore.Mvc;
using Microsoft.Azure.NotificationHubs;

namespace AndroidApi.Controllers
{
    [Route("api/[controller]")]
    [ApiController]
    public class NotificationController : ControllerBase
    {

        private IPlaceQuery places;

        public NotificationController(IPlaceQuery _places)
        {
            this.places = _places;
        }



        [HttpPost("noti")]
        public async Task<IActionResult> Post([FromBody]NotificationJson NotificationMsg)
        {
           
            Microsoft.Azure.NotificationHubs.NotificationOutcome outcome = null;
            HttpStatusCode ret = HttpStatusCode.InternalServerError;

            int count = PlacesController.inProgress.Where(x => x.Name == NotificationMsg.Message).Count();
            if (count == 0)
            {
                PlacesController.inProgress.Add(places.GetFiltereBar(NotificationMsg.Message));

            }
           
            


            Dictionary<string, string> templateParams = new Dictionary<string, string>();
            templateParams["messageParam"] = "Breaking News!";
          
            GcmNotification notification = new GcmNotification("{ \"data\":{\"message\":\"" + NotificationMsg.Message+"\"} }");

            outcome = await Notification.Notification.Instance.Hub.SendNotificationAsync(notification);

            

            if (outcome != null)
            {
                if (!((outcome.State == Microsoft.Azure.NotificationHubs.NotificationOutcomeState.Abandoned) ||
                    (outcome.State == Microsoft.Azure.NotificationHubs.NotificationOutcomeState.Unknown)))
                {
                    ret = HttpStatusCode.OK;
                }
            }

            return Ok("OK");
        }
    }
}
