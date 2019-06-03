package com.group.call.almundo.business;

import com.group.call.almundo.constants.ConstantMessage;
import com.group.call.almundo.entities.EmployeeEntity;
import com.group.call.almundo.enums.EmployeeEnum;
import com.group.call.almundo.enums.StatusEnum;
import com.group.call.almundo.exceptions.CallCenterException;
import com.group.call.almundo.repository.EmployeeRepository;
import com.group.call.almundo.request.RequestCall;
import com.group.call.almundo.response.ResponseCall;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Random;
import java.util.concurrent.*;
import java.util.stream.Collectors;

/**
 * Created by Anderson Q. on 3/06/2019.
 */
@Service
public class Dispatcher extends Thread {

    private static Log LOGGER = LogFactory.getLog(Dispatcher.class);

    @Autowired
    EmployeeRepository employeeRepository;

    ExecutorService executorService = Executors.newFixedThreadPool(10);

    public ResponseCall receiveCall(RequestCall requestCall) throws CallCenterException {
        LOGGER.info("INFO!!! Dispatcher - receiveCall()");
        List<EmployeeEntity> vacantEmployees = getEmployeeByStatus(StatusEnum.VACANT.getStatus());
        ResponseCall responseCall = allocateCall(vacantEmployees);
        return responseCall;
    }

    /**
     * Method to allocate the call to a employee that is vacant
     *
     * @param vacantEmployees           List of employees that are vacants
     * @return ResponseCall             Answer of the operation
     * @throws InterruptedException
     * @throws ExecutionException
     */
    public ResponseCall allocateCall(List<EmployeeEntity> vacantEmployees) throws CallCenterException {
        LOGGER.info("INFO!!! Dispatcher - allocateCall()");
        try {
            ResponseCall responseCall = new ResponseCall();
            List<EmployeeEntity> operatorEmployees = selectEmployeeByPosition(vacantEmployees, EmployeeEnum.OPERATOR.getEmployee());
            List<EmployeeEntity> supervisorEmployees = selectEmployeeByPosition(vacantEmployees, EmployeeEnum.SUPERVISOR.getEmployee());
            List<EmployeeEntity> directorEmployees = selectEmployeeByPosition(vacantEmployees, EmployeeEnum.DIRECTOR.getEmployee());
            if (operatorEmployees != null) {
                return dispatchCall(operatorEmployees);
            } else if (supervisorEmployees != null) {
                return dispatchCall(supervisorEmployees);
            } else if (directorEmployees != null) {
                return dispatchCall(directorEmployees);
            } else {
                responseCall.setMessage(ConstantMessage.THERE_NO_PERSONAL_AVAILABLE);
            }
            return responseCall;
        }catch (Exception e) {
            LOGGER.error("ERROR!!! CallController - allocateCall() - exception() -> " + e.getMessage());
            throw new CallCenterException(e);
        }
    }

    /**
     * Method to get Employee by Status
     *
     * @param status                    Status
     * @return List<EmployeeEntity>     List of employee
     */
    List<EmployeeEntity> getEmployeeByStatus(String status) {
        LOGGER.info("INFO!!! Dispatcher - getEmployeeByStatus() - status -> " + status);
        List<EmployeeEntity> employeeEntityList = employeeRepository.findByStatus(status);
        return employeeEntityList;
    }

    /**
     * Method to select the employee by position
     *
     * @param employeeEntityList        List of employees
     * @param position                  Position (Operator, Supervisor, Director)
     * @return List<EmployeeEntity>     Resulted of List of employees
     */
    List<EmployeeEntity> selectEmployeeByPosition(List<EmployeeEntity> employeeEntityList, String position) {
        LOGGER.info("INFO!!! Dispatcher - selectEmployeeByPosition()");
        if (employeeEntityList == null || employeeEntityList.isEmpty()) return null;
        return employeeEntityList.stream().filter(info -> info.getPosition().equalsIgnoreCase(position)).collect(Collectors.toList());
    }

    /**
     * Method to dispatch the call
     *
     * @param employeeEntityList        List of employees
     * @return ResponseCall             Answer of the operation
     * @throws InterruptedException
     * @throws ExecutionException
     */
    private ResponseCall dispatchCall(List<EmployeeEntity> employeeEntityList) throws CallCenterException {
        LOGGER.info("INFO!!! Dispatcher - dispatchCall()");
        try {
            employeeEntityList.get(0).setStatus(StatusEnum.BUSY.getStatus());
            employeeRepository.save(employeeEntityList.get(0));
            LOGGER.info("INFO!!! Employee -> " + employeeEntityList.get(0).getNameComplete() + " STAYED WITH ASSIGNMENT " );
            int timeLifeCall = calculateTimeCall();
            Callable<String> callable = () -> {
                System.out.println(ConstantMessage.CALL_BEGIN_BY.concat(employeeEntityList.get(0).getNameComplete()).
                        concat(" (").concat(employeeEntityList.get(0).getPosition()).
                        concat(") ").concat("ETC ").concat(String.valueOf(timeLifeCall)));
                Thread.sleep(timeLifeCall);
                return ConstantMessage.CALL_FINISHED_BY.concat(employeeEntityList.get(0).getNameComplete()).concat(" (").
                        concat(employeeEntityList.get(0).getPosition()).concat(")");
            };
            Future<String> future = executorService.submit(callable);
            String result = future.get();
            employeeEntityList.get(0).setStatus(StatusEnum.VACANT.getStatus());
            LOGGER.info("INFO!!! Employee -> " + employeeEntityList.get(0).getNameComplete() + " WAS LEFT WITHOUT A ASSIGNMENT " );
            employeeRepository.save(employeeEntityList.get(0));
            ResponseCall responseCall = new ResponseCall();
            responseCall.setMessage(result);
            return responseCall;
        }catch (Exception e) {
            LOGGER.error("ERROR!!! CallController - dispatchCall() - exception() -> " + e.getMessage());
            throw new CallCenterException(e);
        }
    }

    /**
     * Method to calculate time of call
     * @return Int  Time of call
     */
    private int calculateTimeCall() {
        LOGGER.info("INFO!!! Dispatcher - calculateTimeCall()");
        Random random =  new Random();
        return ((5 + random.nextInt(11 - 5))*1000);
    }

}
