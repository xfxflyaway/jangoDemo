SELECT
concat(a.loan_card_code,",",a.org_code ,",",
 a.loan_card_code ,",",
 a.org_code ,",",
 a.name ,",",
 COUNT(*) ,",",
 SUM(a.amount),",",
 SUM(a.balanceAmount),",",
 COUNT(*),",",
 "空" ,",",
 "空" ,",",
 "空" ,",",
 "空" ,",",
 "空" ,",",
 "空" ,",",
 "空" ,",",
 "空" ,",",
 "空" ,",",
 "空" ,",",
 "空" ,",",
 "空" ,",",
 "空" ,",",
 "空" ,",",
 "空" ,",",
 "空" ,",",
 "空" ,",",
 "空" ,",",
 "空" ,",",
 "空" ,",",
 "空" ,",",
 "空" ,",",
 "空" ,",",
 "空" ,",",
 "空" ,",",
 "空" ,",",
 "空" ,",",
 "空" ,",",
 "空" ,",",
 "空" ,",",
 "空" ,",",
 "空" ,",",
 "空" ,",",
 "6530K990046" ,",",
 a.dt ,",",
 SUM(a.interest),",",
 SUM(convert(a.overdue,DECIMAL(12,2)))
)
FROM(
SELECT
	i.loanee,
   i.loanee_id,
   i.loanee_entity,
   i.amount,
   i.amount - SUM(al.period_refund_principal) balanceAmount,
   i.completed,
   db.dt,
   ce.id,
 	ce.loan_card_code,
 	ce.org_code,
 	ce.name,
   SUM(al.un_refund_interest) interest,SUM(al.un_refund_overdue + al.un_refund_compound) overdue
FROM (SELECT "2019-10-31" dt) db,credit_mid_invoice i
	LEFT JOIN credit_mid_accrued_log al ON al.invoice_id=i.id
	LEFT JOIN credit_enterprise ce ON ce.enterprise_id= i.loanee_id
WHERE
	i.loanee_entity="E" AND al.accrued_date=db.dt AND ce.id IS NOT null
GROUP BY i.id
)a
GROUP BY a.loanee_id