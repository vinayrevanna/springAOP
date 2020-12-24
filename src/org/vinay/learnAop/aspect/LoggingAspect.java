package org.vinay.learnAop.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.vinay.learnAop.model.Circle;

@Aspect
public class LoggingAspect {

	/*
	write as many methods here
	cross cutting concern so called advice 
	logging advice inside a aspect aop
	If we want this logging advice to run before the execution of public string getName()
	any time this signature is called this logging advice is executed automatically
	If we specify the class with package name then only that class getname() will run
	 */

	/*
	 *  @Before(value = "execution(public String getName())")   -- applies to both triangle and circle 
	    @Before(value = "execution(public String org.vinay.learnAop.model.Circle.getName())")  //applies to specifically to circle getter only
		@Before(value="execution(* get*(..))")  //using wildcards to apply   
		"execution(* get*(*))" 			--any get with arguments to all getters
		"execution(* get*(..))"   	    --no arguments
		@Before(value="execution(* org.vinay.learnAop.model.*.get*(..))")  --specific to all models by specifying the package with class
	 */


	@Before(value="allGetters() && onlyCircleMethod()")
	public void LoggingAdvice(JoinPoint jp){
		System.out.println(jp.toString());
		Circle cir = (Circle)jp.getTarget();
	}

	/*Use pointcuts dummy function instead of write the wildcards again and again 
	 * reusing expressions 
	@Before(value="onlyCircleMethod()")
	public void secondAdvice(){
		System.out.println("Second advice to circle executed");
	}
	 */

	/*
	 * point cuts by aop 
	   allGetters is dummy methods that holds the point cut
	 */
	@Pointcut("execution(* get*())")
	public void allGetters(){}

	@Pointcut("execution(* *org.vinay.learnAop.model.Circle.*(..))")
	public void allCircleMethods(){}

	/*
	 * @Pointcut("within(org.vinay.learnAop.model.Circle)")  -- only circle model all getters
	 * @Pointcut("within(org.vinay.learnAop.model..*)")  -- all model and theier subpacakages are included
	 * */
	@Pointcut("within(org.vinay.learnAop.model.Circle)")
	public void onlyCircleMethod(){	}


	/*
	 * Combining two expressions
	 *
	 * 
	@Before(value="onlyCircleMethod() && allGetters()")
	public void secondAdvic(){
		System.out.println("Second advice2 to circle executed");
	}
	 */

	/*
	 * JOIN POINT 
		ALL THE PLACES IN CODE WHERE ADVICE IS APPLIED

		@Before(value="allGetters() && onlyCircleMethod()")
		public void LoggingAdvice(JoinPoint jp){
		System.out.println(jp.toString());
		Circle cir = (Circle)jp.getTarget();
	}
	 */

	/*
	 * when setter is called when function takes a arguments 
	 * this advice is executed when user makes a call not when spring container intiliazes it 
	 * 
	@Before("args(name)")
	public void stringArgumentsMethods(String name){
		System.out.println("A method that takes String arguments has been called "+name);
	}

	 */

	//@After("args(name)")  //executes after the set method is executed success or expection
	//@AfterReturning("args(name)")  //executes afer the set method successfuly executes else no
	@AfterReturning(pointcut="args(name)",returning="returnString")  //tell spring input type and output type
	//public void stringArgumentsMethods(String name,Object returnString){
	public void stringArgumentsMethods(String name,String returnString){
		System.out.println("A method that takes String arguments has been called "+name+"  The output value is "+returnString);

	}

	///@AfterThrowing("args(name)")  //similar to finally
	@AfterThrowing(pointcut="args(name)",throwing="exp") //to access the exception object also
	public void exceptionAdvice(String name, RuntimeException exp){
		System.out.println("An exception has been thrown "+name+" Runtime exception : "+exp);
	}

	/*
	 * Advice types
	 * advice has to take one arg ie ProceedingJoinPoint &
	 * 
	 * */

	//@Around("allGetters()")
	//check what is differce tutorial 33
	@Around("@annotation(org.vinay.learnAop.aspect.Loggable)")  //apply to as many as methods Loggable is interface only for getter using @Loggable in model class
	public Object myAroundAdvice(ProceedingJoinPoint pjp){
		Object returnValue = null;//if target method returns something
		try {
			//before actual execution of code
			System.out.println("Before the advice");
			returnValue = pjp.proceed();  //target method to execute
			//after execution of code
			System.out.println("After returning");
		} catch (Throwable e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("after throwing");
		}

		System.out.println("After finally");
		return returnValue;
	}


	//Naming conventions are important for point cuts throughout the packages
	/*@Pointcut("execution(org.vinay.learnAop.service.*Service.*(..))") 
	public void allServiceMethods(){}//all the Services files
	 */	

	//Custom annotations 







}
