SELECT 
  tr.user_id, 
  u.username, 
  tr.training_id, 
  tr.training_date, 
  count(1) 
FROM 
  users u 
  inner join Training_details tr on u.user_id = tr.user_id 
group by 
  tr.user_id, 
  tr.training_id, 
  tr.training_date 
having 
  count(1) > 1;