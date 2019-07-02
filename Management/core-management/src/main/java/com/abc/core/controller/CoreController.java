package com.abc.core.controller;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import com.abc.core.domain.ApiOperationEnum;
import com.abc.core.exception.InvalidRequestBodyException;
import com.abc.core.exception.ManagementApiFailure;
import com.abc.core.exception.NoDataFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Base controller class which can be extended for other modules.
 *
 * @param <T>
 * @author vijaykumar.s 06/28/2019
 */
@RestController
public abstract class CoreController<T> {

    protected static final Logger LOGGER = LoggerFactory.getLogger(CoreController.class);


    @PostMapping(consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<String> saveInfo(@RequestBody final T info) {
        LOGGER.info(String.format("%s /save : request-%s", RequestMethod.POST, info));
        return ResponseEntity.status(HttpStatus.CREATED)
                .headers(constructResponseHeaders(ApiOperationEnum.SAVE, String.class))
                .body(doPostSaveInfo(info));
    }


/*
    @GetMapping(value = "/byId", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<T> getById(@RequestParam(value = "id") Integer id) {
        LOGGER.info(String.format("%s /get : request-%s", RequestMethod.GET, id));
        return ResponseEntity.status(HttpStatus.OK)
                .headers(constructResponseHeaders(ApiOperationEnum.GET, Object.class))
                .body(dogetInfo(id));
    }
*/


    @PutMapping(consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<T> updateInfo(@RequestParam(value = "id") Integer id,
                                        @RequestBody final T info) throws NoDataFoundException {
        LOGGER.info(String.format("%s /put : request-%s", RequestMethod.PUT, id));
        return ResponseEntity.status(HttpStatus.OK)
                .headers(constructResponseHeaders(ApiOperationEnum.UPDATE, Object.class))
                .body(doUpdateInfo(id, info));

    }


    @DeleteMapping
    public ResponseEntity<Void> deleteInfo(@RequestParam(value = "id") final Integer id) throws NoDataFoundException {
        LOGGER.info(String.format("%s /delete : request-%s", RequestMethod.DELETE, id));
        doDeleteInfo(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT)
                .headers(constructResponseHeaders(ApiOperationEnum.DELETE, Void.class))
                .build();
    }

    @GetMapping(value = "/allInfo", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Map<String, Collection<T>>> getAllPersonInfo() throws NoDataFoundException {
        LOGGER.info(String.format("%s /get all info : request", RequestMethod.GET));
        final Map<String, Collection<T>> allInfoMap = new HashMap<>();
        Collection<T> allInfo = doGetAllInfo();
        allInfoMap.put("persons", allInfo);
        return ResponseEntity.status(HttpStatus.OK)
                .headers(constructResponseHeaders(ApiOperationEnum.GET, Collection.class))
                .body(allInfoMap);
    }


    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NoDataFoundException.class)
    public ManagementApiFailure handleNoDataError(final NoDataFoundException e) {
        return new ManagementApiFailure(ManagementApiFailure.Type.NOT_FOUND, e.getMessage());
    }


    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(InvalidRequestBodyException.class)
    public ManagementApiFailure handleRequestBodyError(final InvalidRequestBodyException e) {
        return new ManagementApiFailure(ManagementApiFailure.Type.BAD_REQUEST, e.getMessage());
    }

    protected abstract String doPostSaveInfo(final T info);

    protected abstract T dogetInfo(final Integer id);

    protected abstract T doUpdateInfo(final Integer id, final T info) throws NoDataFoundException;

    protected abstract void doDeleteInfo(final Integer id) throws NoDataFoundException;

    protected abstract Collection<T> doGetAllInfo() throws NoDataFoundException;

    protected abstract HttpHeaders constructResponseHeaders(final ApiOperationEnum operationType, final Class<?> t);

}
