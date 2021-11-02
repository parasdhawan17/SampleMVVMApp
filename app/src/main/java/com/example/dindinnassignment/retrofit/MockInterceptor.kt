package com.example.dindinnassignment.retrofit

import okhttp3.*
import okhttp3.MediaType.Companion.toMediaTypeOrNull


class MockInterceptor : Interceptor{

    val ordersResponse : String = "{\n" +
            "   \"status\":{\n" +
            "      \"success\":true,\n" +
            "      \"statusCode\":200,\n" +
            "      \"message\":\"success\"\n" +
            "   },\n" +
            "   \"data\":[\n" +
            "      {\n" +
            "         \"id\":10,\n" +
            "         \"title\":\"Special extra large fried rice\",\n" +
            "         \"addon\":[\n" +
            "            {\n" +
            "               \"id\":21,\n" +
            "               \"title\":\"Fried Egg\",\n" +
            "               \"quantity\":3\n" +
            "            }\n" +
            "         ],\n" +
            "         \"quantity\":1,\n" +
            "         \"created_at\":\"2021-06-10T15:00+00Z\",\n" +
            "         \"alerted_at\":\"2021-06-10T15:01+00Z\",\n" +
            "         \"expired_at\":\"2021-06-10T15:02+00Z\"\n" +
            "      },\n" +
            "      {\n" +
            "         \"id\":11,\n" +
            "         \"title\":\"Chicken Noodle\",\n" +
            "         \"addon\":[\n" +
            "            {\n" +
            "               \"id\":26,\n" +
            "               \"title\":\"Extra chicken\",\n" +
            "               \"quantity\":2\n" +
            "            },\n" +
            "            {\n" +
            "               \"id\":27,\n" +
            "               \"title\":\"Sambal\",\n" +
            "               \"quantity\":1\n" +
            "            }\n" +
            "         ],\n" +
            "         \"quantity\":1,\n" +
            "         \"created_at\":\"2021-06-10T15:10+00Z\",\n" +
            "         \"alerted_at\":\"2021-06-10T15:12+00Z\",\n" +
            "         \"expired_at\":\"2021-06-10T15:14+00Z\"\n" +
            "      }\n" +
            "   ]\n" +
            "}"

    val category1 = "{\"data\":[{\n" +
            "    \"categoryId\" : 1,\n" +
            "    \"name\" : \"Olives\",\n" +
            "    \"stock\": 2,\n" +
            "    \"imageURL\": \"www.image.com\"\n" +
            "    },\n" +
            "    {\n" +
            "    \"categoryId\" : 1,\n" +
            "    \"name\" : \"Peanut butter\",\n" +
            "    \"stock\": 3,\n" +
            "    \"imageURL\": \"www.image.com\"\n" +
            "    },\n" +
            "    {\n" +
            "    \"categoryId\" : 1,\n" +
            "    \"name\" : \"Canned tomatoes\",\n" +
            "    \"stock\": 7,\n" +
            "    \"imageURL\": \"www.image.com\"\n" +
            "    },\n" +
            "{\n" +
            "    \"categoryId\" : 1,\n" +
            "    \"name\" : \"Salsa\",\n" +
            "    \"stock\": 7,\n" +
            "    \"imageURL\": \"www.image.com\"\n" +
            "    },\n" +
            "{\n" +
            "    \"categoryId\" : 1,\n" +
            "    \"name\" : \"Tuna fish\",\n" +
            "    \"stock\": 7,\n" +
            "    \"imageURL\": \"www.image.com\"\n" +
            "    }]}"

    val category1NameA = "{\"data\":[{\n" +
            "    \"categoryId\" : 1,\n" +
            "    \"name\" : \"Olives\",\n" +
            "    \"stock\": 2,\n" +
            "    \"imageURL\": \"www.image.com\"\n" +
            "    },\n" +
            "    {\n" +
            "    \"categoryId\" : 1,\n" +
            "    \"name\" : \"Canned tomatoes\",\n" +
            "    \"stock\": 7,\n" +
            "    \"imageURL\": \"www.image.com\"\n" +
            "    },\n" +
            "{\n" +
            "    \"categoryId\" : 1,\n" +
            "    \"name\" : \"Tuna fish\",\n" +
            "    \"stock\": 7,\n" +
            "    \"imageURL\": \"www.image.com\"\n" +
            "    }]}"

    val category2 = "{\"data\":[{\n" +
            "    \"categoryId\" : 2,\n" +
            "    \"name\" : \"Mayonnaise\",\n" +
            "    \"stock\": 8,\n" +
            "    \"imageURL\": \"www.image.com\"\n" +
            "    },\n" +
            "    {\n" +
            "    \"categoryId\" : 2,\n" +
            "    \"name\" : \"Dijon mustard\",\n" +
            "    \"stock\": 3,\n" +
            "    \"imageURL\": \"www.image.com\"\n" +
            "    },\n" +
            "    {\n" +
            "    \"categoryId\" : 2,\n" +
            "    \"name\" : \"Soy sauce\",\n" +
            "    \"stock\": 7,\n" +
            "    \"imageURL\": \"www.image.com\"\n" +
            "    }]}"

    val category2NameA = "{\"data\":[{\n" +
            "    \"categoryId\" : 2,\n" +
            "    \"name\" : \"Mayonnaise\",\n" +
            "    \"stock\": 8,\n" +
            "    \"imageURL\": \"www.image.com\"\n" +
            "    },\n" +
            "    {\n" +
            "    \"categoryId\" : 2,\n" +
            "    \"name\" : \"Soy sauce\",\n" +
            "    \"stock\": 7,\n" +
            "    \"imageURL\": \"www.image.com\"\n" +
            "    }]}"

    val category3 = "{\"data\":[{\n" +
            "    \"categoryId\" :3,\n" +
            "    \"name\" : \"Chili paste\",\n" +
            "    \"stock\": 8,\n" +
            "    \"imageURL\": \"www.image.com\"\n" +
            "    },\n" +
            "    {\n" +
            "    \"categoryId\" : 3,\n" +
            "    \"name\" : \"Hot sauce\",\n" +
            "    \"stock\": 3,\n" +
            "    \"imageURL\": \"www.image.com\"\n" +
            "    },\n" +
            "    {\n" +
            "    \"categoryId\" : 3,\n" +
            "    \"name\" : \"Ketchup\",\n" +
            "    \"stock\": 7,\n" +
            "    \"imageURL\": \"www.image.com\"\n" +
            "    }]}"
    val category3NameA = "{\"data\":[{\n" +
            "    \"categoryId\" :3,\n" +
            "    \"name\" : \"Chili paste\",\n" +
            "    \"stock\": 8,\n" +
            "    \"imageURL\": \"www.image.com\"\n" +
            "    },\n" +
            "    {\n" +
            "    \"categoryId\" : 3,\n" +
            "    \"name\" : \"Ketchup\",\n" +
            "    \"stock\": 7,\n" +
            "    \"imageURL\": \"www.image.com\"\n" +
            "    }]}"

    val allCategories = "{\n" +
            "   \"data\":[\n" +
            "      {\n" +
            "         \"id\":1,\n" +
            "         \"title\":\"Bento\"\n" +
            "      },\n" +
            "      {\n" +
            "         \"id\":2,\n" +
            "         \"title\":\"Main\"\n" +
            "      },\n" +
            "       {\n" +
            "         \"id\":3,\n" +
            "         \"title\":\"Appetizer\"\n" +
            "      }\n" +
            "   ]\n" +
            "}"

    override fun intercept(chain: Interceptor.Chain): Response {
        var responseString: String = ""
        val uri = chain.request().url.toUri()


        when(uri.path.toString()){
            "/" + EndPoints.GET_ORDERS ->{
                responseString = ordersResponse
            }
            "/" + EndPoints.GET_ALL_CATEGORIES ->{
                responseString = allCategories
            }
            "/" + EndPoints.GET_INGREDIENTS_BY_CATEGORY ->{
                responseString = when(uri.query.split("=")[1]){
                    "1" ->category1
                    "2" -> category2
                    "3" -> category3
                    else -> category1
                }
            }
            "/" + EndPoints.GET_INGREDIENTS_BY_NAME ->{
                responseString = category1
            }
            "/" + EndPoints.GET_INGREDIENTS_BY_CATEGORY_AND_NAME ->{
                val firstQuery = uri.query.split("&")[0]
                val categoryId = firstQuery.split("=")[1]
                val secondQuery = uri.query.split("&")[1]
                val name = secondQuery.split("=")[1]

                responseString = if(name.isEmpty()){
                    when(categoryId){
                        "1" ->category1
                        "2" -> category2
                        "3" -> category3
                        else -> category1
                    }
                }else{
                    when(categoryId){
                        "1" ->category1NameA
                        "2" -> category2NameA
                        "3" -> category3NameA
                        else -> category1
                    }
                }

            }
        }


        return  Response.Builder()
            .code(200)
            .message(responseString)
            .request(chain.request())
            .protocol(Protocol.HTTP_1_0)
            .body(
                ResponseBody.create(
                    "application/json".toMediaTypeOrNull(),
                    responseString.toByteArray()
                )
            )
            .addHeader("content-type", "application/json")
            .build()
    }
}