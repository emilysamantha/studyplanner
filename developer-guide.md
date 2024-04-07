# Developer Guide

## Create a Subject

```shell
Invoke-WebRequest -Uri http://localhost:8080/categories/subject -Method POST -Headers @{ "Content-Type" = "application/json" } -Body '{ "title": "Advanced Algorithms", "suggestedEventTitle": "Study for Algorithms", "priority": 1 }'
```

## Create an Event

### Busy Event 

```shell
Invoke-WebRequest -Uri http://localhost:8080/events/busy -Method POST -Headers @{ "Content-Type" = "application/json" } -Body '{ "title": "Afternoon Shift", "date": "2024-04-07", "time": "13:00:00", "duration": "04:00:00" }'
```

### Target Event

```shell
Invoke-WebRequest -Uri http://localhost:8080/events/target -Method POST -Headers @{ "Content-Type" = "application/json" } -Body '{ "title": "Final Exam", "date": "2024-04-14", "time": "09:00:00", "duration": "02:00:00", "numSuggestedEvents": 4 }'
```

## Get a Calendar

```shell
Invoke-WebRequest -Uri http://localhost:8080/calendars/{id} -Method GET
```

### Get a Calendar's Suggested Plan

```shell
Invoke-WebRequest -Uri http://localhost:8080/calendars/{id}/plan -Method GET
```
