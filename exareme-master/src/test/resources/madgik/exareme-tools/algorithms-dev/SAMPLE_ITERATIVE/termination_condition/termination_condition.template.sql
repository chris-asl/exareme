requirevars 'defaultDB';
attach database '%{defaultDB}' as defaultDB;

update iterationsDB.iterations_condition_check_result_tbl set iterations_condition_check_result = (
  select sum_tbl.sum_val < 5
  from defaultDB.sum_tbl
);

select "ok";
