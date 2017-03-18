/*
 * Author		:	VenomVendor
 * Dated		:	07-Nov'13 2:45:48 AM
 * URL			:	https://www.google.co.in/search?q=VenomVendor
 * Copyright(c):	2013 - Present, VenomVendor.
 * License		:	Apache License Version 2.0
 */

package com.venomvendor.sdk.wordpress.network.util;

public class HttpStatus {
    /* HTTP STATUS EQUIVALENT */
    private final static String _100 = "Continue";
    private final static String _101 = "Switching Protocols";
    private final static String _102 = "Processing";
    private final static String _103 = "Checkpoint";
    private final static String _122 = "Request URI too long";
    private final static String _200 = "OK";
    private final static String _201 = "Created";
    private final static String _202 = "Accepted";
    private final static String _203 = "Non-Authoritative Information.";
    private final static String _204 = "No Content";
    private final static String _205 = "Reset Content";
    private final static String _206 = "Partial Content";
    private final static String _207 = "Multi Status";
    private final static String _208 = "Already Reported";
    private final static String _226 = "IM Used";
    private final static String _300 = "Multiple Choices";
    private final static String _301 = "Moved Permanently";
    private final static String _302 = "Found";
    private final static String _303 = "See Other";
    private final static String _304 = "Not Modified";
    private final static String _305 = "Use Proxy";
    private final static String _306 = "Switch Proxy";
    private final static String _307 = "Temporary Redirect";
    private final static String _308 = "Resume Incomplete";
    private final static String _400 = "Bad Request";
    private final static String _401 = "Unauthorized";
    private final static String _402 = "Payment Required";
    private final static String _403 = "Forbidden";
    private final static String _404 = "Page Not Found";
    private final static String _405 = "Method Not Allowed";
    private final static String _406 = "Not Acceptable";
    private final static String _407 = "Proxy Authentication Required";
    private final static String _408 = "Request Timeout";
    private final static String _409 = "Conflict";
    private final static String _410 = "Gone";
    private final static String _411 = "Length Required";
    private final static String _412 = "Precondition Failed";
    private final static String _413 = "Request Entity Too Large";
    private final static String _414 = "Request URI Too Long";
    private final static String _415 = "Unsupported Media Type";
    private final static String _416 = "Requested Range Not Satisfiable";
    private final static String _417 = "Expectation Failed";
    private final static String _422 = "Unprocessable Entity";
    private final static String _423 = "Locked";
    private final static String _424 = "Failed Dependency";
    private final static String _425 = "Unordered Collection";
    private final static String _426 = "Upgrade Required";
    private final static String _428 = "Precondition Required";
    private final static String _429 = "Too Many Requests";
    private final static String _431 = "Request Header Fields Too Large";
    private final static String _444 = "No Response";
    private final static String _449 = "Retry With";
    private final static String _450 = "Blocked By Windows Parental Controls";
    private final static String _451 = "Unavailable For Legal Reasons";
    private final static String _499 = "Client Closed Request";
    private final static String _500 = "Oh, no. Our developers have screwed the server.";
    private final static String _501 = "Not Implemented";
    private final static String _502 = "Bad Gateway";
    private final static String _503 = "Service Unavailable";
    private final static String _504 = "Gateway Timeout";
    private final static String _505 = "HTTP Version Not Supported";
    private final static String _506 = "Variant Also Negotiates";
    private final static String _507 = "Insufficient Storage";
    private final static String _508 = "Loop Detected";
    private final static String _509 = "Bandwidth Limit Exceeded";
    private final static String _510 = "Not Extended";
    private final static String _511 = "Network Authentication Required";
    private final static String _598 = "Network read timeout";
    private final static String _599 = "Network connect timeout error";

    /**
     * @return <b>Status Code's Equivalent</b>
     */
    public static String getStatusEquivalent(int statusCode) {
        String statusCodeDescription;

        switch (statusCode) {
            case 100:
                statusCodeDescription = _100;
                break;

            case 101:
                statusCodeDescription = _101;
                break;

            case 102:
                statusCodeDescription = _102;
                break;

            case 103:
                statusCodeDescription = _103;
                break;

            case 122:
                statusCodeDescription = _122;
                break;

            case 200:
                statusCodeDescription = _200;
                break;

            case 201:
                statusCodeDescription = _201;
                break;

            case 202:
                statusCodeDescription = _202;
                break;

            case 203:
                statusCodeDescription = _203;
                break;

            case 204:
                statusCodeDescription = _204;
                break;

            case 205:
                statusCodeDescription = _205;
                break;

            case 206:
                statusCodeDescription = _206;
                break;

            case 207:
                statusCodeDescription = _207;
                break;

            case 208:
                statusCodeDescription = _208;
                break;

            case 226:
                statusCodeDescription = _226;
                break;

            case 300:
                statusCodeDescription = _300;
                break;

            case 301:
                statusCodeDescription = _301;
                break;

            case 302:
                statusCodeDescription = _302;
                break;

            case 303:
                statusCodeDescription = _303;
                break;

            case 304:
                statusCodeDescription = _304;
                break;

            case 305:
                statusCodeDescription = _305;
                break;

            case 306:
                statusCodeDescription = _306;
                break;

            case 307:
                statusCodeDescription = _307;
                break;

            case 308:
                statusCodeDescription = _308;
                break;

            case 400:
                statusCodeDescription = _400;
                break;

            case 401:
                statusCodeDescription = _401;
                break;

            case 402:
                statusCodeDescription = _402;
                break;

            case 403:
                statusCodeDescription = _403;
                break;

            case 404:
                statusCodeDescription = _404;
                break;

            case 405:
                statusCodeDescription = _405;
                break;

            case 406:
                statusCodeDescription = _406;
                break;

            case 407:
                statusCodeDescription = _407;
                break;

            case 408:
                statusCodeDescription = _408;
                break;

            case 409:
                statusCodeDescription = _409;
                break;

            case 410:
                statusCodeDescription = _410;
                break;

            case 411:
                statusCodeDescription = _411;
                break;

            case 412:
                statusCodeDescription = _412;
                break;

            case 413:
                statusCodeDescription = _413;
                break;

            case 414:
                statusCodeDescription = _414;
                break;

            case 415:
                statusCodeDescription = _415;
                break;

            case 416:
                statusCodeDescription = _416;
                break;

            case 417:
                statusCodeDescription = _417;
                break;

            case 422:
                statusCodeDescription = _422;
                break;

            case 423:
                statusCodeDescription = _423;
                break;

            case 424:
                statusCodeDescription = _424;
                break;

            case 425:
                statusCodeDescription = _425;
                break;

            case 426:
                statusCodeDescription = _426;
                break;

            case 428:
                statusCodeDescription = _428;
                break;

            case 429:
                statusCodeDescription = _429;
                break;

            case 431:
                statusCodeDescription = _431;
                break;

            case 444:
                statusCodeDescription = _444;
                break;

            case 449:
                statusCodeDescription = _449;
                break;

            case 450:
                statusCodeDescription = _450;
                break;

            case 451:
                statusCodeDescription = _451;
                break;

            case 499:
                statusCodeDescription = _499;
                break;

            case 500:
                statusCodeDescription = _500;
                break;

            case 501:
                statusCodeDescription = _501;
                break;

            case 502:
                statusCodeDescription = _502;
                break;

            case 503:
                statusCodeDescription = _503;
                break;

            case 504:
                statusCodeDescription = _504;
                break;

            case 505:
                statusCodeDescription = _505;
                break;

            case 506:
                statusCodeDescription = _506;
                break;

            case 507:
                statusCodeDescription = _507;
                break;

            case 508:
                statusCodeDescription = _508;
                break;

            case 509:
                statusCodeDescription = _509;
                break;

            case 510:
                statusCodeDescription = _510;
                break;

            case 511:
                statusCodeDescription = _511;
                break;

            case 598:
                statusCodeDescription = _598;
                break;

            case 599:
                statusCodeDescription = _599;
                break;

            default:
                statusCodeDescription = "UNKNOWN STATUS CODE";
                break;
        }

        return statusCodeDescription;
    }
}
