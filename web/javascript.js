function openDay(evt, dayName) {
    var i, tabcontent, tablinks;
    tabcontent = document.getElementsByClassName("tabcontent");
    for (i = 0; i < tabcontent.length; i++) {
      tabcontent[i].style.display = "none";
    }
    tablinks = document.getElementsByClassName("tablinks");
    for (i = 0; i < tablinks.length; i++) {
      tablinks[i].className = tablinks[i].className.replace(" active", "");
    }
    document.getElementById(dayName).style.display = "block";
    evt.currentTarget.className += " active";
  }

  var timeJson_values = { 
                        "": 0,
                        "5:00 am" : 5.00,
                        "5:15 am" : 5.25,
                        "5:30 am" : 5.50,
                        "5:45 am" : 5.75,
                        "6:00 am" : 6.00,
                        "6:15 am" : 6.25,
                        "6:30 am" : 6.50,
                        "6:45 am" : 6.75,
                        "7:00 am" : 7.00,
                        "7:15 am" : 7.25,
                        "7:30 am" : 7.50,
                        "7:45 am" : 7.75, 
                        "8:00 am" : 8.00,
                        "8:15 am" : 8.25,
                        "8:30 am" : 8.50,
                        "8:45 am" : 8.75,
                        "9:00 am" : 9.00,
                        "9:15 am" : 9.25,
                        "9:30 am" : 9.50,
                        "9:45 am" : 9.75,
                        "10:00 am" : 10.00,
                        "10:15 am" : 10.25,
                        "10:30 am" : 10.50,
                        "10:45 am" : 10.75,
                        "11:00 am" : 11.00,
                        "11:15 am" : 11.25,
                        "11:30 am" : 11.50,
                        "11:45 am" : 11.75,
                        "12:00 pm" : 12.00,
                        "12:15 pm" : 12.25,
                        "12:30 pm" : 12.50,
                        "12:45 pm" : 12.75,
                        "1:00 pm" : 13.00,
                        "1:15 pm" : 13.25,
                        "1:30 pm" : 13.50,
                        "1:45 pm" : 13.75,
                        "2:00 pm" : 14.00,
                        "2:15 pm" : 14.25,
                        "2:30 pm" : 14.50,
                        "2:45 pm" : 14.75,
                        "3:00 pm" : 15.00,
                        "3:15 pm" : 15.25,
                        "3:30 pm" : 15.50,
                        "3:45 pm" : 15.75,
                        "4:00 pm" : 16.00,
                        "4:15 pm" : 16.25,
                        "4:30 pm" : 16.50,
                        "4:45 pm" : 16.75,
                        "5:00 pm" : 17.00,
                        "5:15 pm" : 17.25,
                        "5:30 pm" : 17.50,
                        "5:45 pm" : 17.75,
                        "6:00 pm" : 18.00,
                        "6:15 pm" : 18.25,
                        "6:30 pm" : 18.50,
                        "6:45 pm" : 18.75,
                        "7:00 pm" : 19.00,
                        "7:15 pm" : 19.25,
                        "7:30 pm" : 19.50,
                        "7:45 pm" : 19.75,
                        "8:00 pm" : 20.00,
                        "8:15 pm" : 20.25,
                        "8:30 pm" : 20.50,
                        "8:45 pm" : 20.75,
                        "9:00 pm" : 21.00,
                        "9:15 pm" : 21.25,
                        "9:30 pm" : 21.50,
                        "9:45 pm" : 21.75,
                        "10:00 pm" : 22.00	
                      };
        
  function getTime(language)
  {
    var element = document.getElementById("url");
    element.value = language;
    element.innerHTML = language;

    startHour = language;    
}

//used to disable or enable an element on the day's tab
function disable(item, value)
{
  var element = document.getElementById(item);
  element.disabled = value;
}

var weekHours = [0,0,0,0,0,0,0];

//used to calculate the hours for a given day
function calculateDay_hours(startTime, endTime, totalHours_day, dayNumber, totalTab_day)
{ 
  //calculate: endtime - start time
  var totalHours = timeJson_values[endTime] - timeJson_values[startTime];
  weekHours[dayNumber] = totalHours; //store this day's calculation
  

  //checkes for negative values
  if(totalHours >= 0)
  {
    totalHours_day.innerText = totalHours;        //display on day tab
    totalTab_day.innerText = totalHours;          //display on total tab
    totalHoursForWeek.innerText = getAllHours();
  }
  else
  {
    totalTab_day.innerText = "";
    totalHours_day.innerText = "######";
  }
}

//used to calculate all hours on startup
function startup_calculateHours()
{
  //Tuesday
  calculateDay_hours( startTime_tuesday.value, endTime_tuesday.value, totalHours_tuesday, 0, tue_hours );
  
  //Wednesday
  calculateDay_hours( startTime_wednesday.value, endTime_wednesday.value, totalHours_wednesday, 1, wed_hours );
  
  //Thursday
  calculateDay_hours( startTime_thursday.value, endTime_thursday.value, totalHours_thursday, 2, thur_hours );
  
  //Friday
  calculateDay_hours( startTime_friday.value, endTime_friday.value, totalHours_friday, 3, fri_hours );
  
  //Saturday
  calculateDay_hours( startTime_saturday.value, endTime_saturday.value, totalHours_saturday, 4, sat_hours );
  
  //Sunday
  calculateDay_hours( startTime_sunday.value, endTime_sunday.value, totalHours_sunday, 5, sun_hours );
  
  //Monday
  calculateDay_hours( startTime_monday.value, endTime_monday.value, totalHours_monday, 6, mon_hours );
}

//used for start / end times for each day
var timeSelect = ["",
  "5:00 am",
  "5:15 am",
  "5:30 am",
  "5:45 am",
  "6:00 am",
  "6:15 am",
  "6:30 am",
  "6:45 am",
  "7:00 am",
  "7:15 am",
  "7:30 am",
  "7:45 am",
  "8:00 am",
  "8:15 am",
  "8:30 am",
  "8:45 am",
  "9:00 am",
  "9:15 am",
  "9:30 am",
  "9:45 am",
  "10:00 am",
  "10:15 am",
  "10:30 am",
  "10:45 am",
  "11:00 am",
  "11:15 am",
  "11:30 am",
  "11:45 am",
  "12:00 pm",
  "12:15 pm",
  "12:30 pm",
  "12:45 pm",
  "1:00 pm",
  "1:15 pm",
  "1:30 pm",
  "1:45 pm",
  "2:00 pm",
  "2:15 pm",
  "2:30 pm",
  "2:45 pm",
  "3:00 pm",
  "3:15 pm",
  "3:30 pm",
  "3:45 pm",
  "4:00 pm",
  "4:15 pm",
  "4:30 pm",
  "4:45 pm",
  "5:00 pm",
  "5:15 pm",
  "5:30 pm",
  "5:45 pm",
  "6:00 pm",
  "6:15 pm",
  "6:30 pm",
  "6:45 pm",
  "7:00 pm",
  "7:15 pm",
  "7:30 pm",
  "7:45 pm",
  "8:00 pm",
  "8:15 pm",
  "8:30 pm",
  "8:45 pm",
  "9:00 pm",
  "9:15 pm",
  "9:30 pm",
  "9:45 pm",
  "10:00 pm"
];
//used for hourly rate values
var dollars = ["$10.00",
"$10.25",
"$10.50",
"$10.75",
"$11.00",
"$11.25",
"$11.50",
"$11.75",
"$12.00",
"$12.25",
"$12.50",
"$12.75",
"$13.00",
"$13.25",
"$13.50",
"$13.75",
"$14.00",
"$14.25",
"$14.50",
"$14.75",
"$15.00",
"$15.25",
"$15.50",
"$15.75",
"$16.00",
"$16.25",
"$16.50",
"$16.75",
"$17.00",
"$17.25",
"$17.50",
"$17.75",
"$18.00",
"$18.25",
"$18.50",
"$18.75",
"$19.00",
"$19.25",
"$19.50",
"$19.75",
"$20.00",
"$20.25",
"$20.50",
"$20.75",
"$21.00",
"$21.25",
"$21.50",
"$21.75",
"$22.00",
"$22.25",
"$22.50",
"$22.75",
"$23.00",
"$23.25",
"$23.50",
"$23.75",
"$24.00",
"$24.25",
"$24.50",
"$24.75",
"$25.00",
"$25.25",
"$25.50",
"$25.75",
"$26.00",
"$26.25",
"$26.50",
"$26.75",
"$27.00",
"$27.25",
"$27.50",
"$27.75",
"$28.00",
"$28.25",
"$28.50",
"$28.75",
"$29.00",
"$29.25",
"$29.50",
"$29.75",
"$30.00",
"$30.25",
"$30.50",
"$30.75",
"$31.00",
"$31.25",
"$31.50",
"$31.75",
"$32.00",
"$32.25",
"$32.50",
"$32.75",
"$33.00",
"$33.25",
"$33.50",
"$33.75",
"$34.00",
"$34.25",
"$34.50",
"$34.75",
"$35.00",
"$35.25",
"$35.50",
"$35.75",
"$36.00",
"$36.25",
"$36.50",
"$36.75",
"$37.00",
"$37.25",
"$37.50",
"$37.75",
"$38.00",
"$38.25",
"$38.50",
"$38.75",
"$39.00",
"$39.25",
"$39.50",
"$39.75",
"$40.00",
"$40.25",
"$40.50",
"$40.75",
"$41.00",
"$41.25",
"$41.50",
"$41.75",
"$42.00",
"$42.25",
"$42.50",
"$42.75",
"$43.00",
"$43.25",
"$43.50",
"$43.75",
"$44.00",
"$44.25",
"$44.50",
"$44.75",
"$45.00",
"$45.25",
"$45.50",
"$45.75",
"$46.00",
"$46.25",
"$46.50",
"$46.75",
"$47.00",
"$47.25",
"$47.50",
"$47.75",
"$48.00",
"$48.25",
"$48.50",
"$48.75",
"$49.00",
"$49.25",
"$49.50",
"$49.75"
];

//populate times for each day.
function populateData(select, dataSource) 
{ 
  for(let i = 0; i<dataSource.length; i++)
  {   
    var option = new Option(dataSource[i]+"",dataSource[i]+"");    
    select.append(option);
  }
}
function populateLists()
{
  //Tuesday
  populateData(document.getElementById('startTime_tuesday'),  timeSelect);
  populateData(document.getElementById('endTime_tuesday'),    timeSelect);

  //Wednesday
  populateData(document.getElementById('startTime_wednesday'),  timeSelect);
  populateData(document.getElementById('endTime_wednesday'),    timeSelect);
  
  //Thursday
  populateData(document.getElementById('startTime_thursday'), timeSelect);
  populateData(document.getElementById('endTime_thursday'),   timeSelect);
  
  //Friday
  populateData(document.getElementById('startTime_friday'), timeSelect);
  populateData(document.getElementById('endTime_friday'),   timeSelect);
  
  //Saturday
  populateData(document.getElementById('startTime_saturday'), timeSelect);
  populateData(document.getElementById('endTime_saturday'),   timeSelect);
  
  //Sunday
  populateData(document.getElementById('startTime_sunday'), timeSelect);
  populateData(document.getElementById('endTime_sunday'),   timeSelect);
  
  //Monday
  populateData(document.getElementById('startTime_monday'), timeSelect);
  populateData(document.getElementById('endTime_monday'),   timeSelect);

  //hourly rate dropdown
  populateData(document.getElementById('hourly_rate'), dollars);

  //set starting indexes for times
  setIndex();
}

//used to sett the start and end times for all days.
//these values will later come from a file, which comes from the user's settings
var starting_value = 13;
var endTime_value = 45;  
function setIndex()
{
  //Tuesday
  document.getElementById("startTime_tuesday").selectedIndex = starting_value;
  document.getElementById("endTime_tuesday").selectedIndex = endTime_value;
 
  //Wednesday
  document.getElementById("startTime_wednesday").selectedIndex = starting_value;
  document.getElementById("endTime_wednesday").selectedIndex = endTime_value;
  
  //Thursday
  document.getElementById("startTime_thursday").selectedIndex = starting_value;
  document.getElementById("endTime_thursday").selectedIndex = endTime_value;
  
  //Friday
  document.getElementById("startTime_friday").selectedIndex = starting_value;
  document.getElementById("endTime_friday").selectedIndex = endTime_value;
  
  //Saturday
  document.getElementById("startTime_saturday").selectedIndex = 0;
  document.getElementById("endTime_saturday").selectedIndex = 0;
  
  //Sunday
  document.getElementById("startTime_sunday").selectedIndex = 0;
  document.getElementById("endTime_sunday").selectedIndex = 0;
  
  //Monday
  document.getElementById("startTime_monday").selectedIndex = starting_value;
  document.getElementById("endTime_monday").selectedIndex = endTime_value;
}
function resetIndexToDefault(start, end)
{
  document.getElementById(start).selectedIndex = starting_value;
  document.getElementById(end).selectedIndex = endTime_value;  
}

//changeStartTime(timeSelect);   //DEBUG  - used to display index of times in console



//gets all hours, hourly rate, and calculates what is owed to the worker
function calculate_week_total_plus_hourlyRate()
{
  var hours_ofAllDays = getAllHours(),
      hourlyRate_string = document.getElementById('hourly_rate').value,
      hourlyRate = hourlyRate_string.split("$"),
      total = hourlyRate[1] * hours_ofAllDays;

      //calculate total and rounds to 2 decimal place  
      total_owed.innerText = "$"+total.toFixed(2);
}

//logging
function logDayHours()
{
  for(let i=0; i<weekHours.length; i++)
  {
    console.log(weekHours[i]);
  }
}

//calculate all hours
function getAllHours()
{
  var total =0;
  for(let i=0; i<weekHours.length; i++)
  {
    if(weekHours[i] > -1)
    {
      total += weekHours[i];
    }
    else
    {
      return 0;
    }
  }

  return total;
}

//used as a simple way to change the start and end times by showing the index connected to each time value.
function changeStartTime(dataSource)
{
  for(let i =0; i<timeSelect.length; i++)
  {
    console.log( timeSelect[i]+" : "+i);
  }
}

populateLists();
startup_calculateHours();