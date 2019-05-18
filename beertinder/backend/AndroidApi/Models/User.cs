using System;
using System.Collections.Generic;
using System.ComponentModel.DataAnnotations;
using System.Linq;
using System.Threading.Tasks;

namespace AndroidApi.Models
{
    public class User
    {
        [Key]
        public int UserID { get; set; }
        [Required]
        public string UID { get; set; }
        [StringLength(50)]

        public string Latitude { get; set; }
        [StringLength(50)]
        public string Longitude { get; set; }
        public bool IsOnline { get; set; }
    }
}
