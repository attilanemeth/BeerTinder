using Microsoft.Azure.NotificationHubs;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace AndroidApi.Notification
{
    public class Notification
    {
        public static Notification Instance = new Notification();

        public NotificationHubClient Hub { get; set; }

        private Notification()
        {
            Hub = NotificationHubClient.CreateClientFromConnectionString("Endpoint=sb://beertindernotinamespace.servicebus.windows.net/;SharedAccessKeyName=DefaultFullSharedAccessSignature;SharedAccessKey=Nh8xDc1THo45je3ZahWSKfyXMPbzMPhCrdX41edjSj8=",
                                                                            "beertinderNoti");
        }

    }
}
