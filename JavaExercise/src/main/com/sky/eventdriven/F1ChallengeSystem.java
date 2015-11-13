package com.sky.eventdriven;

import java.util.ArrayList;
import java.util.List;

import com.loic.algo.eventDrive.Event;
import com.loic.algo.eventDrive.EventDriveSystem;
/**
 * {@link http://www.geeksforgeeks.org/google-interview-question-for-java-position/} <br>
 * The solution will be evaluated on following parameters.
      Object Oriented Design aspects of the solution.
      Overall coding practices.
      Working test cases of the solution.

You can use Ant/Maven as build tools for the solution, Junit, Mockito or other testing frameworks.
You may also include a brief explanation of your design and assumptions along with your code.

Problem Statement: In a Formula-1 challenge, there are n teams numbered 1 to n. Each team has a car and a driver. Car’s specification are as follows:
– Top speed: (150 + 10 * i) km per hour
– Acceleration: (2 * i) meter per second square.
– Handling factor (hf) = 0.8
– Nitro : Increases the speed to double or top speed, whichever is less. Can be used only once.

Here i is the team number.
The cars line up for the race. The start line for (i + 1)th car is 200 * i meters behind the ith car.

All of them start at the same time and try to attain their top speed. A re-assessment of the positions is done every 2 seconds(So even if the car has crossed the finish line in between, you’ll get to know after 2 seconds). During this assessment, each driver checks if there is any car within 10 meters of his car, his speed reduces to: hf * (speed at that moment). Also, if the driver notices that he is the last one on the race, he uses ‘nitro’.

Taking the number of teams and length of track as the input, Calculate the final speeds and the corresponding completion times.
 */
public class F1ChallengeSystem extends EventDriveSystem
{
	private final List<Car> mCarsList;
	private final int maxDis;
	
	private Car champion;
	
	public F1ChallengeSystem(int N, int maxDis)
	{
		super();
		this.maxDis = maxDis;
		mCarsList = new ArrayList<F1ChallengeSystem.Car>(N);
		for(int i=1; i<=N; i++)
		{
			Car car = new Car(i, (150 + 10 * i) / 3.6d, 2*i);
			car.record.dis = -200 * (i-1);
			mCarsList.add(car);
			
			addNewEvent(new AcceleratStopEvent(0, car));
		}
		addNewEvent(new ReassessmentEvent(2));
	}

	@Override
	protected boolean isFinish()
	{
		return champion != null;
	}

	@Override
	protected void onStopSimulate()
	{
		Log.debug("the champion is {}", champion);
	}

	@Override
	protected void processEvent(Event event)
	{
		if(event instanceof AcceleratStopEvent)
		{
			Car car = ((AcceleratStopEvent)event).car;
			double duration = (event.time - car.record.recordTime);
			car.record.recordTime = event.time;
			car.record.dis += (car.record.curSpeed * duration + 0.5f * car.acceleration * duration * duration);
			car.record.curSpeed = car.topSpeed;
			//Log.debug("Time :{} - {} stop accelerat and in top speed now",event.time, car);
		}
		else if (event instanceof ReassessmentEvent) 
		{
			addNewEvent(new ReassessmentEvent(event.time + 2));
			//Log.debug("new rea-ssessment Event at time : {}", event.time);
			Car lastCar = mCarsList.get(0);
			for(Car car : mCarsList)
			{
				double duration = (event.time - car.record.recordTime);
				if(car.record.curSpeed < car.topSpeed)
				{
					car.record.dis += (car.record.curSpeed * duration + 0.5d * car.acceleration * duration * duration);
					car.record.curSpeed += car.acceleration * (event.time - car.record.recordTime);
				}
				else 
				{
					car.record.dis += car.topSpeed * duration;
				}
				car.record.curSpeed *= car.handlingFactor;
				car.record.recordTime = event.time;
				//Log.debug("new record for {}", car);
				
				addNewEvent(new AcceleratStopEvent(event.time, car));
				
				if(car.record.dis < lastCar.record.dis)
				{
					lastCar = car;
				}
				
				if(car.record.dis >= maxDis)
				{
					if(champion == null || car.record.dis > champion.record.dis)
					{
						champion = car;
					}
				}
			}
			if(lastCar.nitro())
			{
				Log.debug("{} use nitro ...", lastCar);
				if(lastCar.record.curSpeed < lastCar.topSpeed)
				{
					addNewEvent(new AcceleratStopEvent(event.time, lastCar));
				}
			}
		}
	}
	
	//innner class
	private static class CarRecord
	{
		private double recordTime = 0;
		private double dis = 0;
		private double curSpeed = 0;
		private boolean alreadyNitro = false;
		
		@Override
		public String toString()
		{
			return "CarRecord [recordTime=" + recordTime + ", dis=" + dis
					+ ", curSpeed=" + curSpeed + ", alreadyNitro="
					+ alreadyNitro + "]";
		}
	}
	
	private static class Car
	{
		private final int id;
		private final double topSpeed;
		private final int acceleration;
		private final float handlingFactor = 0.8f;
		
		private CarRecord record = new CarRecord();
		
		public Car(int id, double topSpeed, int acceleration)
		{
			this.id = id;
			this.topSpeed = topSpeed;
			this.acceleration = acceleration;
		}
		
		private double computeAccelerateDuration()
		{
			return (topSpeed - record.curSpeed) / (double)acceleration;
		}
		
		public boolean nitro()
		{
			if(! record.alreadyNitro)
			{
				record.curSpeed = Math.min(record.curSpeed * 2,  topSpeed);
				record.alreadyNitro = true;
				return true;
			}
			return false;
		}

		@Override
		public String toString()
		{
			return "Car " + id + " - " + record;
		}
	}
	
	private static class AcceleratStopEvent extends Event
	{
		private Car car;

		public AcceleratStopEvent(double startTime, Car car)
		{
			super(startTime + car.computeAccelerateDuration());
			this.car = car;
		}

		@Override
		public boolean isValid()
		{
			return Math.abs(car.computeAccelerateDuration() - this.time + car.record.recordTime) < 0.0000001d;
		}
	}
	
	private static class ReassessmentEvent extends Event
	{
		public ReassessmentEvent(double time)
		{
			super(time);
		}

		@Override
		public boolean isValid()
		{
			return true;
		}
	}

}
