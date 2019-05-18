using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using AndroidApi.DataContext;
using AndroidApi.Models;

namespace AndroidApi.DatabaseOperations
{
    public class PlacesQuery : IPlaceQuery
    {
        private AppDataContext dataContext;

        public PlacesQuery(AppDataContext app)
        {
            this.dataContext = app;
        }
        public List<Bar> GetBars()
        {
            return dataContext.Bars.ToList();
        }

        public Bar GetFiltereBar(string bar)
        {
            var res = dataContext.Bars.Where(x => x.Name == bar).FirstOrDefault();
            return res;
        }
    }
}
