import React, { useEffect, useState } from "react";
import { View, Text, Button, TextInput, StyleSheet, Alert } from "react-native";

const LoginScreen = (props) => {

    const [username, setUsername] = useState();
    const [password, setPassword] = useState();
    const [userId, setUserId] = useState();

    // // test
    // useEffect(() => {
    //     console.log("LoginScreen", userId);
    // }, [userId])

    const handleSubmit = async () => {
        if (username == null || password == null || username == "" || password == "") {
            Alert.alert(
                "Missing Fields",
                "Enter username and password, then press submit again",
                [
                    {
                        text: "Cancel",
                        style: "cancel"
                    },
                    { 
                        text: "OK",
                    }
                ]
            );
        }
        else {
            // check server to validate user
            fetch(`http://localhost:8080/login?username=${username}&password=${password}`)
                .then(async (response) => {
                    if (response.status == 200) {
                        // get the player's ID from response
                        let loginId = await response.json();
                        props.route.params.loggedIn = true;
                        props.navigation.navigate("Home", {
                            loggedIn: props.route.params.loggedIn,
                            id: loginId
                        })
                    }
                    else {
                        Alert.alert(
                            "Incorrect Username or Password",
                            "Try again, then press submit",
                            [
                                {
                                    text: "Cancel",
                                    style: "cancel"
                                },
                                { 
                                    text: "OK",
                                }
                            ]
                        );
                    }
                })

            // props.route.params.loggedIn = true;
            // props.navigation.navigate("Home", {
            //     loggedIn: props.route.params.loggedIn
            // })
        }
    }

    return(
        <View style={styles.main}>
             <Text style={styles.text}>Username:</Text>
            <TextInput
                style={styles.input}
                onChangeText={setUsername}
                value={username}
                placeholder="Username..."
            />
             <Text style={styles.text}>Password:</Text>
            <TextInput
                style={styles.input}
                onChangeText={setPassword}
                value={password}
                placeholder="Password..."
            />
            <View style={styles.buttonView}>
                <Button
                    title="Submit"
                    onPress={() => 
                        handleSubmit()
                    }
                />
            </View>
        </View>
    )
}

const styles = StyleSheet.create({
    main: {
        paddingTop: "7.5%",
    },
    input: {
      height: 40,
      margin: 12,
      borderWidth: 1,
      padding: 10,
      borderRadius: 10,
    },
    text: {
        textAlign: "center",
        fontSize: 20,
    },
    buttonView: {
        borderWidth: 0.5,
        marginLeft: "auto",
        marginRight: "auto",
        marginTop: "5%",
        padding: "1%",
        borderRadius: 10,
        borderColor: "blue",
    },
  });

export default LoginScreen;
