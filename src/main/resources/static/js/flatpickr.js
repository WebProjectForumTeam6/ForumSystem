// npm package: flatpickr
// github link: https://github.com/flatpickr/flatpickr

$(function() {
  'use strict';

  // date picker 
  if($('#flatpickr-date').length) {
    flatpickr("#flatpickr-date", {
      wrap: true,
      dateFormat: "Y-m-d",
    });
  }


  // time picker
  if($('#flatpickr-time').length) {
    flatpickr("#flatpickr-time", {
      wrap: true,
      enableTime: true,
      noCalendar: true,
      dateFormat: "H:i",
    });
  }
});

// npm package: flatpickr
// github link: https://github.com/flatpickr/flatpickr

$(function() {
  'use strict';

  // date picker
  if($('#flatpickr-date1').length) {
    flatpickr("#flatpickr-date1", {
      wrap: true,
      dateFormat: "Y-m-d",
    });
  }


  // time picker
  if($('#flatpickr-time1').length) {
    flatpickr("#flatpickr-time1", {
      wrap: true,
      enableTime: true,
      noCalendar: true,
      dateFormat: "H:i",
    });
  }
});