--drop table public.departments;

SELECT * FROM public.employees order by salary desc

select * from public.departments

select dept_id, max(salary) as "higestSalary" from public.employees
where salary < 9000 group by dept_id --having max(salary) < 9000

--Second highest salary without using limit/top/ROWNUM
--This is using in clause
select max(salary) from public.employees where salary not in (select max(distinct salary) from public.employees);
--This is using subquery < instead of IN clause
select max(salary) from public.employees where salary < (select max(distinct salary) from public.employees);

--Second Highest Salary using Correlated SubQuery
--For each record processed by outer query, inner query will be executed 
--and will return how many records has records has salary less than the current salary. 
--If you are looking for second highest salary then your query will stop as soon as inner query will return 2. 
select id, salary from public.employees e
where 1 = (select count(distinct salary) from public.employees p where e.salary <= p.salary)

--Second Maximum Salary in MySQL using LIMIT
select salary from (select salary from public.employees order by salary desc limit 2) as emp order by salary limit 1;

create table public.departments (
	id numeric, 
	dept_name text,
	CONSTRAINT dept_k PRIMARY KEY(id)
)

CREATE TABLE public.employees
(
  id numeric,
  dept_id numeric,
  name text,
  salary numeric,
  CONSTRAINT emp_pk PRIMARY KEY(id),
  CONSTRAINT emp_dept_fk FOREIGN KEY (dept_id)
      REFERENCES public.departments(id)
)

insert into public.departments values(100, 'Admin');
insert into public.departments values(101, 'Technology');


insert into public.employees values(1, 100, 'Kristeen',1420);
insert into public.employees values(2, 100, 'Ashley',2006);
insert into public.employees values(3, 100, 'Julia',2210);
insert into public.employees values(4, 100, 'Maria',3000);
insert into public.employees values(5, 101, 'Anba',6000);
insert into public.employees values(6, 101, 'Chand',9500);
insert into public.employees values(7, 101, 'Rajiv',9000);
insert into public.employees values(8, 101, 'Herbert',4000);

