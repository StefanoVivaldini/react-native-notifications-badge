import { View, StyleSheet, Button } from 'react-native';
import {
  clearNotificationsBadgeCount,
  setNotificationsBadgeCount,
} from 'react-native-notifications-badge';
import { requestNotifications } from 'react-native-permissions';
import { useEffect } from 'react';

export default function App() {
  useEffect(() => {
    requestNotifications(['badge', 'alert', 'sound']).then(({ status }) => {
      console.log('Notification permission status:', status);
    });
  }, []);

  return (
    <View style={styles.container}>
      <Button
        title={'set count'}
        onPress={() => setNotificationsBadgeCount(4)}
      />
      <Button
        title={'clear count'}
        onPress={() => clearNotificationsBadgeCount()}
      />
    </View>
  );
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    alignItems: 'center',
    justifyContent: 'center',
  },
});
