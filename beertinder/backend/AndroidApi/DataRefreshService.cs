using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading;
using System.Threading.Tasks;

namespace AndroidApi
{
    public class DataRefreshService : HostedService
    {
       

       

       

        protected override async Task ExecuteAsync(CancellationToken cancellationToken)
        {
            while (!cancellationToken.IsCancellationRequested)
            {
                Controllers.PlacesController.inProgress.Clear();
                await Task.Delay(TimeSpan.FromHours(1), cancellationToken);
            }
        }
    }
}
