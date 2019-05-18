using AndroidApi.Models;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace AndroidApi.DatabaseOperations
{
    public interface IPlaceQuery
    {
        List<Bar> GetBars();

        Bar GetFiltereBar(string bar);
    }
}
